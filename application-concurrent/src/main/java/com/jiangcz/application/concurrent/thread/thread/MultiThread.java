package com.jiangcz.theory.thread.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 类名称：MultiThread<br>
 * 类描述：<br>
 * [8] JDWP Command Reader // JDWP是Java Debug Wire Protocol的缩写，是Java Debug使用的一种协议
 * [7] JDWP Event Helper Thread
 * [6] JDWP Transport Listener: dt_socket
 * [5] Attach Listener       //负责接收到外部的命令，对该命令进行执行然后把结果返回给发送者
 * [4] Signal Dispatcher     //分发处理发送给JVM信号的线程
 * [3] Finalizer             //调用对象finalize方法的线程
 * [2] Reference Handler     //清除Reference的线程
 * [1] main                  //main线程，用户程序入口
 * 1.8.0_60                  //jdk版本号
 *
 *
 * 创建时间：2018年08月29日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */
public class MultiThread{
    public static void main(String[] args) {
        // 获取java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        //遍历线程信息,仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos){
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
        System.out.println(System.getProperty("java.version"));
    }
}

