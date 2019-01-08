package com.codingprh.springcloud.order_server.repository;


import com.codingprh.springcloud.order_server.entity.OrderMaster;
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