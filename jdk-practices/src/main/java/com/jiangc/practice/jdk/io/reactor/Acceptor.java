package com.jiangc.practice.jdk.io.reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Acceptor extends NioEventHandler {

    public Acceptor(Selector selector, SelectionKey key) {
        super(selector, key);
    }

    @Override
    public void handler() throws IOException {
        // 把客人接到座位上
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        // 然后告诉客人，如果要点菜，就呼叫"点餐员"
        SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);
        key.attach(new ReadEventHandler(selector, key));
    }
}
