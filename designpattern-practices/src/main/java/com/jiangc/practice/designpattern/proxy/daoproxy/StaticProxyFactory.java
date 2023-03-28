package com.jiangc.practice.designpattern.proxy.daoproxy;


import com.jiangc.practice.designpattern.proxy.bean.User;
import com.jiangc.practice.designpattern.proxy.dao.UserDAO;

/**
 * 类名称：StaticProxyFactory<br>
 * 类描述：<br>
 * 创建时间：2018年08月27日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class StaticProxyFactory implements UserDAO {

    private UserDAO target;

    public StaticProxyFactory(UserDAO target){
        this.target = target;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public void save() {
        System.out.println("开启事务");//扩展了额外功能
        target.save();
        System.out.println("提交事务");
    }
}
