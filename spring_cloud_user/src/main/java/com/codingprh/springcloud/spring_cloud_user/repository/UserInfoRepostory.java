package com.codingprh.springcloud.spring_cloud_user.repository;


import com.codingprh.springcloud.spring_cloud_user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by codingprh
 */
public interface UserInfoRepostory extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openid);
}
