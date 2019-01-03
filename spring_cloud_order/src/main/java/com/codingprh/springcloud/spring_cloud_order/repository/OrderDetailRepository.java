package com.codingprh.springcloud.spring_cloud_order.repository;

import com.codingprh.springcloud.spring_cloud_order.entity.OrderDetail;
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