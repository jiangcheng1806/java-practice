package com.jiangcz.application.designpattern.ioc;

/**
 * 类名称：Car<br>
 * 类描述：<br>
 * 创建时间：2019年09月16日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Car {
    private String name;
    private String length;
    private String width;
    private String height;
    private Wheel wheel;

    // 省略其他不重要代码


    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", wheel=" + wheel +
                '}';
    }
}