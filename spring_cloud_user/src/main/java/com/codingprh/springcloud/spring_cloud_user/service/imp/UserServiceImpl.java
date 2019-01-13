package com.codingprh.springcloud.spring_cloud_user.service.imp;

import com.codingprh.springcloud.spring_cloud_user.entity.UserInfo;
import com.codingprh.springcloud.spring_cloud_user.repository.UserInfoRepostory;
import com.codingprh.springcloud.spring_cloud_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author codingprh
 * @create 2019-01-13 12:22 PM
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoRepostory userInfoRepostory;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepostory.findByOpenid(openid);
    }
}