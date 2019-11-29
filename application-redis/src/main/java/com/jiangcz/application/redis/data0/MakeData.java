package com.jiangcz.application.redis.data0;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名称：com.jiangcz.application.http.data0.MakeData<br>
 * 类描述：<br>
 * 创建时间：2019年09月19日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class MakeData {
    public static void main(String[] args) {


        Node1 node1 = new Node1();
        node1.setSize(3500);
        List<Node0> data = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            Node0 node0 = new Node0();
            node0.setCardNo("42900619860404"+i);
            node0.setDriverName("张三"+i);
            data.add(node0);
        }
        node1.setData(data);

        String s = JSON.toJSONString(node1);
        System.out.println(s);

    }
}
