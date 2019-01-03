package com.codingprh.springcloud.spring_cloud_order.service.impl;

import com.codingprh.springcloud.spring_cloud_order.constant.RedisConstant;
import com.codingprh.springcloud.spring_cloud_order.converter.ObjectToMapConverter;
import com.codingprh.springcloud.spring_cloud_order.dto.OrderDTO;
import com.codingprh.springcloud.spring_cloud_order.entity.OrderDetail;
import com.codingprh.springcloud.spring_cloud_order.entity.OrderMaster;
import com.codingprh.springcloud.spring_cloud_order.enums.OrderStatusEnum;
import com.codingprh.springcloud.spring_cloud_order.enums.PayStatusEnum;
import com.codingprh.springcloud.spring_cloud_order.exception.OrderException;
import com.codingprh.springcloud.spring_cloud_order.repository.OrderDetailRepository;
import com.codingprh.springcloud.spring_cloud_order.repository.OrderMasterRepository;
import com.codingprh.springcloud.spring_cloud_order.service.OrderService;
import com.codingprh.springcloud.spring_cloud_order.utils.JsonUtil;
import com.codingprh.springcloud.spring_cloud_order.utils.KeysUtils;
import com.codingprh.springcloud.spring_cloud_product.client.ProductClient;
import com.codingprh.springcloud.spring_cloud_product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.codingprh.springcloud.spring_cloud_order.enums.OrderExceptionEnum.*;

/**
 * 描述:
 *
 * @author codingprh
 * @create 2018-12-25 5:56 PM
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductClient productClient;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private ExecutorService threadPoolTaskExecutor;


    /**
     * 设计秒杀场景：
     * 使用Redis和队列：不要直接穿透到db层
     * <p>
     * todo:发新订单信息到队列 vs 采用线程池的方式 同步订单
     */
    private void sendOrderMsg() {


    }

    @Override
    public synchronized OrderDTO create(OrderDTO orderDTO) {
        createProcess(orderDTO);
        return orderDTO;

    }

    /**
     * todo:
     * 第一版的秒杀设计，不足，需要完善：
     * 0、库存信息要以db为准，redis只是预减库存
     * 1、不能这穿透db入库订单，而是在product服务中判断db是否足够，再创建订单
     * 2、在create之前，加上redis的保护机制
     *
     * @param orderDTO
     * @return
     */
    @Transactional
    public OrderDTO createProcess(OrderDTO orderDTO) {
        String orderId = KeysUtils.generateUniqueKey();
        orderDTO.setOrderId(orderId);

        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        List<String> productIds = orderDetailList.stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> productInfoOutputList = new ArrayList<>();

        try {
            //把object对象转换为map，并且存入redis
//            for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
//                Map<String, String> map = ObjectToMapConverter.Object2MapString(productInfoOutput);
//
//                redisTemplate.opsForHash().putAll(String.format(RedisConstant.PRODUCT_TEMPLATE, productInfoOutput.getProductId()), map);
//                redisTemplate.expire(String.format(RedisConstant.PRODUCT_TEMPLATE, productInfoOutput.getProductId()), 1, TimeUnit.DAYS);
//            }
            //异步：从redis读取商品信息
            for (String productId : productIds) {
                ProductInfoOutput productInfoOutput = redisMap2ProductInfoOutput(productId);

                if (Objects.isNull(productInfoOutput.getProductId())) {
                    //保护机制：如果商品在redis找不到，从数据库中查找
                    List<ProductInfoOutput> dbProductList = productClient.listForOrder(Arrays.asList(productId));
                    productInfoOutputList.addAll(dbProductList);
                    //加入到缓存中，一天！！
                    for (ProductInfoOutput dbproductInfoOutput : dbProductList) {
                        Map<String, String> map = ObjectToMapConverter.Object2MapString(dbproductInfoOutput);

                        redisTemplate.opsForHash().putAll(String.format(RedisConstant.PRODUCT_TEMPLATE, dbproductInfoOutput.getProductId()), map);
                        redisTemplate.expire(String.format(RedisConstant.PRODUCT_TEMPLATE, dbproductInfoOutput.getProductId()), 1, TimeUnit.DAYS);
                    }

                } else {

                    productInfoOutputList.add(productInfoOutput);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new OrderException(PRODUCT_NOT_FOUND);
        }


        //计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDetailList) {
            for (ProductInfoOutput productInfo : productInfoOutputList) {
                if (Objects.equals(orderDetail.getProductId(), productInfo.getProductId())) {
                    //计算单价
                    orderAmout = new BigDecimal(orderDetail.getProductQuantity()).multiply(productInfo.getProductPrice()).add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeysUtils.generateUniqueKey());
                }
            }
        }
        //订单详情批量入库：穿透数据库

        //同步：扣库存(调用商品服务)
//        productClient.decreaseStock(orderDetailList.stream().map(
//                e -> {
//                    DecreaseStockInput decreaseStockInput = new DecreaseStockInput();
//                    BeanUtils.copyProperties(e, decreaseStockInput);
//                    return decreaseStockInput;
//                }
//        ).collect(Collectors.toList()));

        //todo:同步加锁

        for (OrderDetail orderDetail : orderDetailList) {
            //异步：扣库存（redis）
            //使用listIterator迭代器可以修改和删除元素
            ListIterator iterator = productInfoOutputList.listIterator();
            while (iterator.hasNext()) {
                ProductInfoOutput productInfoOutput = (ProductInfoOutput) iterator.next();
                if (Objects.equals(orderDetail.getProductId(), productInfoOutput.getProductId())) {
                    System.out.println("========================");
                    System.out.println("========================");
                    System.out.println("进入了减redis库存方法，stock=" + productInfoOutput.getProductStock());
                    System.out.println("========================");
                    System.out.println("========================");
                    Integer quantity = orderDetail.getProductQuantity();
                    Integer productStock = productInfoOutput.getProductStock() - quantity;
                    if (productStock < 0) {
                        throw new OrderException(INVENTORY_SHORTAGE);
                    }
                    redisTemplate.opsForHash().put(String.format(RedisConstant.PRODUCT_TEMPLATE, orderDetail.getProductId()), "productStock", productStock.toString());
                    productInfoOutput.setProductStock(productStock);
                    iterator.set(productInfoOutput);
                }
            }
        }


        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.WAIT.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        //异步订单入库：直接穿透db
        threadPoolTaskExecutor.submit(() -> {
            orderDetailRepository.saveAll(orderDetailList);
            orderMasterRepository.save(orderMaster);
        });

        //异步发送消息到mq

        threadPoolTaskExecutor.submit(() -> {
            amqpTemplate.convertAndSend("orderWait", JsonUtil.toJson(productInfoOutputList));
        });

        return orderDTO;
    }

    /**
     * 读取redis中存储的商品信息：map结构
     * 转换为对象
     *
     * @param productId
     * @return
     */
    public ProductInfoOutput redisMap2ProductInfoOutput(String productId) {
        try {
            Map<Object, Object> objectProductInfoOutputMap = redisTemplate.opsForHash().entries(String.format(RedisConstant.PRODUCT_TEMPLATE, productId));
            Map<String, Object> mapProductInfoOutput = new HashMap<>();
            objectProductInfoOutputMap.entrySet().stream().forEach(x -> mapProductInfoOutput.put(x.getKey().toString(), (x.getValue())));
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            org.apache.commons.beanutils.BeanUtils.populate(productInfoOutput, mapProductInfoOutput);
            return productInfoOutput;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new OrderException(MAP_CONVERTER_OBJECT_ERROR);
        }
    }
}