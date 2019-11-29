package com.jiangcz.application.concurrent.future;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 类名称：ConnectionPool<br>
 * 类描述：<br>
 * 创建时间：2018年08月27日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class ConnectionPool {

    private ConcurrentHashMap<String,Connection> pool = new ConcurrentHashMap<>();

    public Connection getConnection(String key){ //我们用了ConcurrentHashMap，这样就不必把getConnection方法置为synchronized(当然也可以用Lock)，当多个线程同时调用getConnection方法时，性能大幅提升。
        Connection conn = null;
        if (pool.containsKey(key)){
            conn = pool.get(key);
        } else {
            conn = createConnection();
            pool.putIfAbsent(key,conn);
        }
        return conn;
    }

    public Connection createConnection(){
        return new Connection();
    }

    class Connection{}
}
