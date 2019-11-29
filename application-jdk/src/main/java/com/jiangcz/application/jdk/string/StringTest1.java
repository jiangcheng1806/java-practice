package com.jiangcz.application.jdk.string;

/**
 * 类名称：StringTest1<br>
 * 类描述：<br>
 * 创建时间：2019年09月12日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class StringTest1 {
    public static void main(String[] args) {
        String aa = "AA";
        String bb = "BB";
        String ccdd = "CC"+"DD";
        String neeff = new String("EE")+new String("FF");
        String aabb = aa+bb;
        String gghh = "GG"+new String("HH");
        aa.intern();
        ccdd.intern();
        neeff.intern();
        aabb.intern();
        gghh.intern();
        System.out.println(aa.intern()==aa);
        System.out.println(neeff.intern()=="EEFF");
        System.out.println("EEFF"==neeff);
        String nccdd = new String("CCDD");
        System.out.println(ccdd==nccdd);
        System.out.println(ccdd==nccdd.intern());
        System.out.println(aabb.intern()==aabb);
        System.out.println(gghh==gghh.intern());
    }
}
