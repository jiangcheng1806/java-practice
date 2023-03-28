package com.jiangc.practices.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 类名称：UDPServer<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class UDPServer {

    public static void main(String[] args) {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(9090);
            byte[] b = new byte[1024];
            DatagramPacket packet = new DatagramPacket(b,0,b.length);
            ds.receive(packet);

            String str = new String(packet.getData(),0,packet.getLength());
            System.out.println(str);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ds != null){
                ds.close();
            }
        }
    }
}
