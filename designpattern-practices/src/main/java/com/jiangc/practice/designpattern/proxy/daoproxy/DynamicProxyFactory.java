package com.jiangc.practice.designpattern.proxy.daoproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 类名称：DynamicProxyFactory<br>
 * 类描述：<br>
 * 创建时间：2018年08月27日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class DynamicProxyFactory {

    private Object target; // 维护一个目标对象

    public DynamicProxyFactory(Object target){
        this.target = target;
    }

    //为目标对象生成代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("开启事务");

                //执行目标对象方法
                Object returnValue = method.invoke(target,args);

                System.out.println("提交事务");
                return null;
            }
        });
    }
}
