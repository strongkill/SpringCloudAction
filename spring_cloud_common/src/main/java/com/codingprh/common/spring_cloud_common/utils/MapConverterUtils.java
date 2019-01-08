package com.codingprh.common.spring_cloud_common.utils;

import com.codingprh.common.spring_cloud_common.exception.CommonUtilsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 * Map转换工具集
 *
 * @author codingprh
 * @create 2018-12-31 2:59 PM
 */
@Slf4j
@Component
public class MapConverterUtils {
    /**
     * 把object对象转换为map对象
     *
     * @param object
     * @return
     */
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
            throw new CommonUtilsException("object转map发生异常");
        }
    }

    /**
     * todo:把list集合转换为map
     *
     * @param list
     * @return
     */
    public static Map<String, Object> list2Map(List<Object> list) {
        //return list.stream().collect(Collectors.toMap(ProductInfoOutput::getProductId, (p) -> p));
        return null;

    }


}