package com.codingprh.springcloud.order_server.repository;


import com.codingprh.springcloud.order_server.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * <p>
 * 订单——商品详细dao层
 *
 * @author codingprh
 * @create 2018-12-25 5:24 PM
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

}