package com.jiangcz.application.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 类名称：FutureConnectionPool<br>
 * 类描述：<br>
 * 创建时间：2018年08月27日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class FutureConnectionPool {

    private ConcurrentHashMap<String,FutureTask<Connection>> pool = new ConcurrentHashMap<>();

    public Connection getConnection(String key) throws ExecutionException, InterruptedException {
        FutureTask<Connection> connectionTask = pool.get(key);
        if (connectionTask != null){
            return connectionTask.get();
        } else {
            Callable<Connection> callable = new Callable<Connection>() {
                @Override
                public Connection call() throws Exception {
                    return createConnection();
                }
            };
            FutureTask<Connection> newTask = new FutureTask<>(callable);
            connectionTask = pool.putIfAbsent(key,newTask); //第一个线程执行pool.putIfAbsent方法后返回null，然后connectionTask被赋值，接着就执行run方法去创建连接，最后get。后面的线程执行pool.putIfAbsent方法不会返回null，就只会执行get方法。
            if (connectionTask == null){
                connectionTask = newTask;
                connectionTask.run();
            }
            return connectionTask.get();
        }
    }

    public Connection createConnection(){
        return new Connection();
    }

    class Connection{}
}
