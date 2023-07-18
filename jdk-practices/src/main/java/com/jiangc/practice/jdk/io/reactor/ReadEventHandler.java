package com.jiangc.practice.jdk.io.reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ReadEventHandler extends NioEventHandler {

    public ReadEventHandler(Selector selector, SelectionKey key) {
        super(selector, key);
    }

    @Override
    public void handler() throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        // 点菜
        String msg = readFromChannel(socketChannel);
        // 你也可以在这里招呼厨房开始做菜
        // TODO mainServer
        // 然后告诉客人菜做好了就会上
        // socketChannel.register(selector, SelectionKey.OP_WRITE);
    }

    private String readFromChannel(SocketChannel socketChannel) {
        return null;
    }
}