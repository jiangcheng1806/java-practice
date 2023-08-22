package com.jiangcz.application.designpattern.callback.callback1;

/**
 * 类名称：FooBar<br>
 * 类描述：<br>
 * 创建时间：2019年12月13日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class FooBar {
    // 组合聚合原则
    private CallBack callBack;
    // 设置接口函数
    public void setCallBack(CallBack callBack){
        this.callBack = callBack;
        doSth();
    }

    private void doSth() {
        System.out.println("FooBar 执行");
        callBack.execute();
    }
}
