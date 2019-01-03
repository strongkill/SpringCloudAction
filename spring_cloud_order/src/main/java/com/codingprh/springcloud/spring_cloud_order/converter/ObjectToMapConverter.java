package com.codingprh.springcloud.spring_cloud_order.converter;

import com.codingprh.springcloud.spring_cloud_order.exception.OrderException;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.codingprh.springcloud.spring_cloud_order.enums.OrderExceptionEnum.OBJECT_CONVERTER_MAP_ERROR;

/**
 * 描述:
 * 对象转map
 *
 * @author codingprh
 * @create 2018-12-31 2:59 PM
 */
@Slf4j
public class ObjectToMapConverter {

    public static Map<String, String> Object2MapString(Object object) {
        try {
            Map<String, String> map = new HashMap<>();
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(object);

                    map.put(key, value.toString());
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new OrderException(OBJECT_CONVERTER_MAP_ERROR);
        }
    }
}