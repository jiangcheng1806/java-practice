package com.jiangc.practice.designpattern.proxy.dao.impl;


import com.jiangc.practice.designpattern.proxy.bean.User;
import com.jiangc.practice.designpattern.proxy.dao.UserDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名称：UserDAOImpl<br>
 * 类描述：<br>
 * 创建时间：2018年08月08日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */
@Repository
public class UserDAOImpl implements UserDAO {

    private static Map<Integer, User> users;

    static {
        users = new HashMap<>(3);
        users.put(1,new User(1,"张三"));
        users.put(2,new User(2,"李四"));
        users.put(3,new User(3,"王五"));
    }

    @Override
    public User getUserById(int id) {
        return users.get(id);
    }

    @Override
    public void save() {
        System.out.println("保存数据");
    }
}
