package com.jiangc.practice.designpattern.flyweight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * 享元模式
 *
 * 通过连接池的管理，实现了数据库连接的共享，不需要每一次都重新创建连接，节省了数据库重新创建的开销，提升了系统的性能！
 * @author jiangcheng-wb
 *
 */
public class ConnectionPool {

	private Vector<Connection> pool;
	
	/*公有属性*/
	private String url = "jdbc:mysql://local:3306/test";
	
	private String username = "root";
	
	private String password = "root";
	
	private String driverClassName = "com.mysql.jdbc.Driver";
	
	private int poolSize = 100;
	
	private static ConnectionPool instance = null;
	
	Connection conn = null;
	
	/*构造方法，做一些初始化工作*/
	private ConnectionPool(){
		
		pool = new Vector<Connection>(poolSize);
		
		for(int i = 0;i < poolSize; i++){
			try {
				Class.forName(driverClassName);
				conn = DriverManager.getConnection(url, username, password);
				pool.add(conn);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/*返回连接到连接池*/
	public synchronized void release(){
		pool.add(conn);
	}
	
	/*返回连接池中的一个数据库连接*/
	public synchronized Connection getConnection(){
		if(pool.size() > 0){
			Connection conn = pool.get(0);
			pool.remove(conn);
			return conn;
		} else {
			return null;
		}
	}
}
