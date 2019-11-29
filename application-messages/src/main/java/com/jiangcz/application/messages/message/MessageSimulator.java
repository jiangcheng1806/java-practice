package com.jiangcz.application.messages.message;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 类名称：MessageSimulator<br>
 * 类描述：消息模拟<br>
 * 创建时间：2019年04月19日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class MessageSimulator {
    //消息队列
    public static ArrayBlockingQueue<Message> messageQueue = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) {
        com.jiangcz.application.messages.message.WindowSimulator generator = new com.jiangcz.application.messages.message.WindowSimulator(messageQueue);

        //产生消息
        generator.GenerateMsg();

        //消息循环
        Message msg = null;
        while ((msg = messageQueue.poll()) != null){
            ((MessageProcess)msg.getSource()).doMessage(msg);
        }
    }
}
