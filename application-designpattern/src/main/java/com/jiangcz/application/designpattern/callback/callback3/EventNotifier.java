package com.jiangcz.application.designpattern.callback.callback3;

import java.util.ArrayList;
import java.util.List;

public class EventNotifier {
    private List<Call> callList = new ArrayList<>();

    public void register(Call call){
        callList.add(call);
    }

    public void doWork(){
        for (Call call: callList){
            call.happen("sample event");
        }
    }
}
