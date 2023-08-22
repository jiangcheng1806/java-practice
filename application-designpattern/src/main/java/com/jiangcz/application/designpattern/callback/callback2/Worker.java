package com.jiangcz.application.designpattern.callback.callback2;

public class Worker {
    private Provider provider;
    public void setProvider(Provider provider){
        this.provider = provider;
    }

    private Boolean flag = true;

    public void doWork(){
        System.out.println("starting to do work");
        if (flag){
            // 事件发生时，通过调用接口的这个方法来通知
            provider.customCallBack();
        }
    }
}
