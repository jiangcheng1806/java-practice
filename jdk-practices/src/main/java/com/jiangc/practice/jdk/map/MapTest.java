package com.jiangc.practice.jdk.map;

import com.jiangc.practice.common.util.JacksonUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String,String> map = new LinkedHashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(""+i,""+i+"----");
        }
        System.out.println(JacksonUtils.console(map));
    }
}
