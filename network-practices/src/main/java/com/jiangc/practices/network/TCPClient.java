package com.jiangc.practices.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 类名称：TCPClient<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class TCPClient {

    public static void main(String[] args) {
        Socket socket = null;
        OutputStream os = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"),9090);
            os = socket.getOutputStream();
            os.write("我是客户端".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
