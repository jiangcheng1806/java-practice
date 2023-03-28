package com.jiangc.practice.designpattern.factory.abstractfactory2_level3;

/**
 * 类名称：Implements2Factory<br>
 * 类描述：<br>
 * 创建时间：2018年08月17日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Implements2Factory implements ServiceFactory {
    @Override
    public Service getService() {
        return new Implements2();
    }
}
