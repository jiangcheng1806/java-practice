package com.jiangcz.application.redis.data1;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名称：MakeData<br>
 * 类描述：<br>
 * 创建时间：2019年09月20日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class MakeData {
    public static void main(String[] args) {
        Node1 node1 = new Node1();
        node1.setSize(3500);
        node1.setBatchNo("2019091903");
        List<Node0> data = new ArrayList<>();
        for (int i = 0; i < 3500; i++) {
            Node0 node0 = new Node0();
            node0.setTreeIds("123551;123552;123567");//鄂A55667
            node0.setVehicleNo("鄂A55667"+i);
            node0.setVehicleType(i%4);
            data.add(node0);
        }
        node1.setData(data);
        String s = JSON.toJSONString(node1);
        System.out.println(s);
    }
}
