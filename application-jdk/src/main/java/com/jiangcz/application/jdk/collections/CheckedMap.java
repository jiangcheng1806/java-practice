package com.jiangcz.theory.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 类名称：CheckedMap<br>
 * 类描述：<br>
 * 创建时间：2018年08月25日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class CheckedMap {

    public static void main(String[] args) {

        HashMap hMap = new HashMap();
        hMap.put("1","A");
        hMap.put("2","B");
        hMap.put("3",2);
        hMap.put("4","D");
        hMap.put("5","E");

        Map<String,String> thmap = Collections.checkedMap(hMap,String.class,String.class);

        System.out.println("\"Dynamically typesafe view of the map: " + thmap);
    }
}
