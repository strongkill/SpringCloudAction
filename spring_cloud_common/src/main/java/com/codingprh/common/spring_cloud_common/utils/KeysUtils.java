package com.codingprh.common.spring_cloud_common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 描述:
 * 生成唯一id
 * todo:在分布式环境下，使用synchronize不能保证唯一id。
 * 应该写成一个服务去调用，并且是使用分布式锁完成
 *
 * @author codingprh
 * @create 2018-12-27 8:59 AM
 */
public class KeysUtils {
    /**
     * 采用时间戳的形式
     *
     * @return
     */
    public static synchronized String generateUniqueKey() {
        Integer randomNum = new Random().nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(randomNum);
    }

    /**
     * 生成uuid，把-符号全部替换
     *
     * @return
     */
    public static synchronized String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}