package com.jiangc.practice.designpattern.proxy.daoproxy;

import com.jiangc.practice.designpattern.proxy.dao.UserDAO;
import com.jiangc.practice.designpattern.proxy.dao.impl.UserDAOImpl;
import org.junit.Test;

/**
 * 类名称：ProxyTest<br>
 * 类描述：<br>
 * 创建时间：2018年08月27日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class ProxyTest {
    @Test
    public void testStaticProxy(){
        //目标对象
        UserDAO target = new UserDAOImpl();
        //代理对象
        StaticProxyFactory proxy = new StaticProxyFactory(target);
        proxy.save();
    }

    @Test
    public void testDynamicProxy(){
        UserDAO target = new UserDAOImpl();
        System.out.println(target.getClass());  //输出目标对象信息
        UserDAO proxy = (UserDAO) new DynamicProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass()); //输出代理对象信息
        proxy.save(); //执行代理方法
    }

    @Test
    public void testCglibProxy(){
        //目标对象
        UserDAO target = new UserDAOImpl();
        System.out.println(target.getClass());
        //代理对象
        UserDAO proxy = (UserDAO) new CglibProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());
        //执行代理对象方法
        proxy.save();
    }
}
