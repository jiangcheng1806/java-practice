package com.jiangcz.application.designpattern.ioc;

/**
 * 类名称：Wheel<br>
 * 类描述：<br>
 * 创建时间：2019年09月16日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */
public class Wheel {
    private String brand;
    private String specification ;

    // 省略其他不重要代码

    @Override
    public String toString() {
        return "Wheel{" +
                "brand='" + brand + '\'' +
                ", specification='" + specification + '\'' +
                '}';
    }
}