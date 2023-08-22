package com.jiangcz.application.jdk.structure.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        Map<String,String> map = new HashMap<>();
        map.put("aa","bvb");
        System.out.println(map);

        map.put("aa","aaa");
        System.out.println(map);

        map.clear();
        System.out.println(map);
        map.put("aa","bbb");
        System.out.println(map);


    }
}
