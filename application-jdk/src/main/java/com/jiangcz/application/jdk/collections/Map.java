package com.jiangcz.theory.collections;

import java.util.HashMap;

/**
 * 类名称：Map<br>
 * 类描述：<br>
 * 创建时间：2018年08月27日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Map {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("1","A");
        hashMap.put("1","B");
        System.out.println(hashMap);
    }
}
