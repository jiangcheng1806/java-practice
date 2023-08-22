package com.jiangcz.application.designpattern.callback.callback2;

public class CustomProvider implements Provider {
    @Override
    public void customCallBack() {
        System.out.println("custom call back msg");
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        //使用匿名类，调用自定义方法
        worker.setProvider(new Provider() {
            @Override
            public void customCallBack() {
                System.out.println("anonymous call back msg");
            }
        });

        worker.doWork();

        worker = new Worker();
        worker.setProvider(new CustomProvider(){
            @Override
            public void customCallBack() {
                super.customCallBack();
                System.out.println("additional call back msg");
            }
        });

        worker.doWork();
    }
}
