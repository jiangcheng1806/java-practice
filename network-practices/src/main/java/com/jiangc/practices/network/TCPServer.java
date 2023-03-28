package com.jiangc.practices.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 类名称：TCPServer<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class TCPServer {

    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket s = null;
        InputStream is = null;
        try {
            ss = new ServerSocket(9090);
            s = ss.accept();
            is = s.getInputStream();
            byte[] b = new byte[20];
            int len;
            while ((len = is.read(b)) != -1){
                String str = new String(b,0,len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (s != null){
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ss != null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
