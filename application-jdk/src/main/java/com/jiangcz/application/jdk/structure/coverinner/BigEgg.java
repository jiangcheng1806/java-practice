package com.jiangcz.application.jdk.structure.coverinner;

/**
 * 类名称：BigEgg<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class BigEgg extends Egg {
    public class Yolk {
        public Yolk(){
            System.out.println("BigEgg.Yolk");
        }
    }

    public static void main(String[] args) {
        new BigEgg();
    }
}
