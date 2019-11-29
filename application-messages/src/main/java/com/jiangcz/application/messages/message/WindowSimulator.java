package com.jiangcz.application.messages.message;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 类名称：WindowSimulator<br>
 * 类描述：窗口模拟类<br>
 * 创建时间：2019年04月19日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class WindowSimulator implements MessageProcess {

    private ArrayBlockingQueue msgQueue;

    public WindowSimulator(ArrayBlockingQueue msgQueue) {
        this.msgQueue = msgQueue;
    }

    public void GenerateMsg(){
        while (true){
            Scanner scanner = new Scanner(System.in);
            int msgType = scanner.nextInt();
            if (msgType < 0){
                //输入负数 结束循环
                break;
            }

            String msgInfo = scanner.next();
            Message msg = new Message(this,msgType,msgInfo);
            try {
                msgQueue.put(msg); //新消息加入到队尾
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 消息模拟
     * @param msg
     */
    @Override
    public void doMessage(Message msg) {

        switch (msg.getType()){
            case Message.KEY_MSG:
                onKeyDown(msg);
                break;
            case Message.MOUSE_MSG:
                onMouseDown(msg);
                break;
            default:
                onSysEvent(msg);
        }
    }

    //键盘事件
    public static void onKeyDown(Message msg){
        System.out.println("键盘事件:");
        System.out.println("type:" + msg.getType());
        System.out.println("info:" + msg.getInfo());
    }

    //鼠标事件
    public static void onMouseDown(Message msg){
        System.out.println("鼠标事件:");
        System.out.println("type:" + msg.getType());
        System.out.println("info:" + msg.getInfo());
    }

    //操作系统产生的消息
    public static void onSysEvent(Message msg){
        System.out.println("系统事件：");
        System.out.println("type:" + msg.getType());
        System.out.println("info:" + msg.getInfo());
    }
}
