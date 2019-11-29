package com.jiangcz.application.designpattern.aop;

import java.lang.reflect.Proxy;

/**
 * 类名称：SimpleAop<br>
 * 类描述：<br>
 * 创建时间：2019年09月16日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class SimpleAop {
    public static Object getProxy(Object bean, Advice advice) {
        return Proxy.newProxyInstance(SimpleAop.class.getClassLoader(),
                bean.getClass().getInterfaces(), advice);
    }
}
