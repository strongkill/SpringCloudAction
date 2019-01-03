package com.codingprh.springcloud.spring_cloud_order.repository;

import com.codingprh.springcloud.spring_cloud_order.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 主订单dao层
 *
 * @author codingprh
 * @create 2018-12-25 5:26 PM
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

}