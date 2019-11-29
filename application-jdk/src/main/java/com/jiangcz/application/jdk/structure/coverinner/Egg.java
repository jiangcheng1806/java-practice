package com.jiangcz.application.jdk.structure.coverinner;

/**
 * 类名称：Egg<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Egg {
    protected class Yolk{
        public Yolk(){
            System.out.println("Egg.Yolk()");
        }
    }

    private Yolk y;

    public Egg(){
        System.out.println("New Egg");
        y = new Yolk();
    }
}
