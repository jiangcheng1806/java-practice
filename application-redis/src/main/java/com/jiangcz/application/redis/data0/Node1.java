package com.jiangcz.application.redis.data0;

import java.io.Serializable;
import java.util.List;

/**
 * 类名称：com.jiangcz.application.http.data0.Node1<br>
 * 类描述：<br>
 * 创建时间：2019年09月19日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Node1 implements Serializable {
    private List<Node0> data;
    private Integer size;

    public List<Node0> getData() {
        return data;
    }

    public void setData(List<Node0> data) {
        this.data = data;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "com.jiangcz.application.http.data0.Node1{" +
                "data=" + data +
                ", size=" + size +
                '}';
    }
}
