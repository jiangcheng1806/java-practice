package com.jiangcz.application.designpattern.callback.callback3;

public class CallTest {
    public static void main(String[] args) {
        EventNotifier notifier = new EventNotifier();
        Call call1 = new Call("call a");
        Call call2 = new Call("call b");

        notifier.register(call1);
        notifier.register(call2);

        notifier.doWork();
    }
}
