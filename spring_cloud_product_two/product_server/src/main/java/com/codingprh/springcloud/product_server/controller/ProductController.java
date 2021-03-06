package com.codingprh.springcloud.product_server.controller;


import com.codingprh.common.spring_cloud_common.entity.ResultVO;
import com.codingprh.common.spring_cloud_common.utils.ResultVOUtils;
import com.codingprh.springcloud.product_common.entity.DecreaseStockInput;
import com.codingprh.springcloud.product_common.entity.ProductInfoOutput;
import com.codingprh.springcloud.product_server.VO.ProductInfoVO;
import com.codingprh.springcloud.product_server.VO.ProductVO;

import com.codingprh.springcloud.product_server.entity.ProductCategory;
import com.codingprh.springcloud.product_server.entity.ProductInfo;
import com.codingprh.springcloud.product_server.service.ProductCategoryService;
import com.codingprh.springcloud.product_server.service.ProductInfoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 描述:
 * product的控制器
 *
 * @author codingprh
 * @create 2018-12-24 1:57 PM
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 1. 查询所有在架的商品
     * 2. 获取在架的商品的类目type列表
     * 3. 查询类目
     * 4. 构造数据
     */
    @GetMapping("/list")
    public ResultVO<ProductVO> list(HttpServletRequest request) {
        //1. 查询所有在架的商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //2. 获取在架的商品的类目type列表
        List<Integer> productCategoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        //3. 查询类目
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(productCategoryTypeList);
        //4.构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {

            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> infoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {

                if (Objects.equals(productInfo.getCategoryType(), productCategory.getCategoryType())) {

                    ProductInfoVO infoVO = new ProductInfoVO();

                    BeanUtils.copyProperties(productInfo, infoVO);

                    infoVOList.add(infoVO);

                }
            }
            productVO.setProductInfoVOList(infoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtils.success(productVOList);


    }

    /**
     * 提供给订单服务使用
     * 根据商品Id查询商品信息
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {
        return productInfoService.findList(productIdList);
    }

    /**
     * 提供给商品服务
     * 减库存
     *
     * @param decreaseStockInputList
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        productInfoService.decreaseStock(decreaseStockInputList);
    }

}