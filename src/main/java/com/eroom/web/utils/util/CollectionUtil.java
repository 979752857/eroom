package com.eroom.web.utils.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

public class CollectionUtil {

    public static boolean isEmpty(Collection<?> collection) {
        if (null == collection) {
            return true;
        } else {
            return collection.isEmpty();
        }
    }

    public static boolean isEmpty(Object[] objects) {
        return (objects == null || objects.length == 0) ? true : false;
    }

    /**
     * 数组转换为List
     * 
     * @param arr
     * @return
     */
    public static List<Object> arrayToList(Object[] arr) {
        List<Object> list = new ArrayList<Object>();
        if (arr == null)
            return list;
        list = Arrays.asList(arr);
        return list;
    }

    /**
     * map转为对象
     * 
     * @return
     * @author tendy
     */
    public static Object mapToObject(Map<String, String> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;
        BeanInfo beanInfo = Introspector.getBeanInfo(beanClass); // 获取类属性
        Object obj = beanClass.newInstance(); // 创建 JavaBean 对象
        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                String value = map.get(propertyName);
                if (value == null || value.equals("null")) {
                    continue;
                }
                Object[] args = new Object[1];
                String typeName = descriptor.getPropertyType().getName();
                switch (typeName) {
                case "java.lang.Long":
                case "long":
                    args[0] = Long.parseLong(value);
                    break;
                case "java.lang.Integer":
                case "int":
                    args[0] = Integer.parseInt(value);
                    break;
                case "java.lang.Byte":
                case "byte":
                    args[0] = Byte.parseByte(value);
                    break;
                case "java.math.BigDecimal":
                    args[0] = new BigDecimal(value);
                    break;
                case "java.util.Date":
                    args[0] = DateUtils.parseDate(value,
                            new String[] { DateUtil.YYYY_MM_DD_HH_MM_SS, DateUtil.YYYYMMDD_HH_MM_SS,
                                    DateUtil.YYYY_MM_DD_HH_MM_SS_SSS, DateUtil.YYYY_MM_DD });
                    break;
                default:
                    args[0] = value;
                }
                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }
}