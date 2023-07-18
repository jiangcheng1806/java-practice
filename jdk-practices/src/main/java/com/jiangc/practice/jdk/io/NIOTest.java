package com.jiangc.practice.jdk.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOTest {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        // 最开始店里没人，肯定只关心有新客人来的情况
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            // 这时候没啥事，在这等人叫
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                it.remove();
                SelectionKey key = it.next();
                if (key.isAcceptable()) {
                    // accept
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()) {
                    // read
                    String msg = readFromChannel((SocketChannel) key.channel());
                    System.out.println(msg);
                }
            }
        }
    }

    private static String readFromChannel(SocketChannel channel) {
        return null;
    }
}
