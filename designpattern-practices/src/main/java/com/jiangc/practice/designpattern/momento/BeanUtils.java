package com.jiangc.practice.designpattern.momento;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 类名称：BeanUtils<br>
 * 类描述：
 *
 *
 * 多状态的备忘录模式 新增一个BeanUtil工具类
 *
 *
 * <br>
 * 创建时间：2018年08月28日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class BeanUtils {

    //把bean的所有属性及数值放入到Hashmap中
    public static HashMap<String,Object> backupProp(Object bean){
        HashMap<String,Object> result = new HashMap<>();
        try {
            //获得Bean描述
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            //获得属性描述
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            //遍历所有属性
            for(PropertyDescriptor des : descriptors){
                //属性名称
                String fieldName = des.getName();
                //读取属性的方法
                Method getter = des.getReadMethod();
                //读取属性值
                Object fieldValue = getter.invoke(bean,new Object[]{});
                if (!fieldName.equalsIgnoreCase("class")){
                    result.put(fieldName,fieldValue);
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;
    }


    //把HashMap的值返回到bean中
    public static void restoreProp(Object bean,HashMap<String,Object> propMap){
        try{
            //获得Bean描述
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            //获得属性描述
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            //遍历所有属性
            for (PropertyDescriptor des : descriptors){
                //属性名称
                String fieldName = des.getName();
                //如果有这个属性
                if (propMap.containsKey(fieldName)){
                    //写属性的方法
                    Method setter = des.getWriteMethod();
                    setter.invoke(bean,new Object[]{propMap.get(fieldName)});
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            //异常处理
            System.out.println("shit");
            e.printStackTrace();
        }
    }
}
