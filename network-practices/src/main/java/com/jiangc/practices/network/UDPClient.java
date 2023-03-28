package com.jiangc.practices.network;

import java.net.*;

/**
 * 类名称：UDPClient<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
            byte[] b = "我是数据".getBytes();
            DatagramPacket packet = new DatagramPacket(b,0,b.length,InetAddress.getByName("127.0.0.1"),9090);

            ds.send(packet);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            if (ds != null){
                ds.close();
            }
        }
    }
}
