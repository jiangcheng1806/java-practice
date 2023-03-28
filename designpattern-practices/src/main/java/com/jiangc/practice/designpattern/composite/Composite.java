package com.jiangc.practice.designpattern.composite;

import java.util.ArrayList;

/**
 * 类名称：Composite<br>
 * 类描述：<br>
 * 创建时间：2018年08月28日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Composite extends Component {
    //构建容器
    private ArrayList<Component> components = new ArrayList<>();
    //增加一个叶子构件或树枝构件
    public void add(Component component){
        this.components.add(component);
    }
    //删除一个叶子构件或树枝构件
    public void remove(Component component){
        this.components.remove(component);
    }
    //获得分支下的所有叶子构件和树枝构件
    public ArrayList<Component> getChildren(){
        return this.components;
    }
}

abstract class Component{}