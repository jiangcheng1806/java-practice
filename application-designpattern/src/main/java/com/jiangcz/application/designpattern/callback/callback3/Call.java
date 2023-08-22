package com.jiangcz.application.designpattern.callback.callback3;

public class Call implements Event {

    private String e;
    public Call(String e){
        this.e = e;
    }

    @Override
    public void happen(String e) {
        System.out.println("event e is happend");
    }


}
