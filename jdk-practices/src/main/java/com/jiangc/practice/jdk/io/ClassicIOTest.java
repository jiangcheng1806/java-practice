package com.jiangc.practice.jdk.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ClassicIOTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true){
            // 给一桌客人服务完之后站在门口傻等着
            Socket socket = serverSocket.accept();
            // 在桌子前傻等着客人点菜
            InputStream in = socket.getInputStream();
            byte[] b = new byte[in.available()];
            int index = in.read(b);
            StringBuffer sb = new StringBuffer();
            while (index != -1) {
                sb.append(new String(b));
            }

            OutputStream out = socket.getOutputStream();
            out.write("server speaking".getBytes());
            socket.close();
        }
    }
}
