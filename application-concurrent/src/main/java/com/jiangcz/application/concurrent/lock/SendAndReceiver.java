package com.jiangcz.application.concurrent.lock;

import java.util.concurrent.Exchanger;

/**
 * 类名称：SendAndReceiver<br>
 * 类描述：对象交换器  主要是Exchanger类   <br>
 * 创建时间：2018年08月01日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class SendAndReceiver {
    private final Exchanger<StringBuilder> exchanger = new Exchanger<>();
    private class Sender implements Runnable{

        @Override
        public void run() {
            try{
                StringBuilder content = new StringBuilder("Hello");
                content = exchanger.exchange(content);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private class Receiver implements Runnable{

        @Override
        public void run() {
            try {
                StringBuilder content = new StringBuilder("World");
                content = exchanger.exchange(content);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void exchange(){
        new Thread(new Sender()).start();
        new Thread(new Receiver()).start();
    }
}
