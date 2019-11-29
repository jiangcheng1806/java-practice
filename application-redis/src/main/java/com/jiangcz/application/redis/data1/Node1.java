package com.jiangcz.application.redis.data1;

import java.io.Serializable;
import java.util.List;

/**
 * 类名称：Node1<br>
 * 类描述：<br>
 * 创建时间：2019年09月20日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Node1 implements Serializable {
    private List<Node0> data;
    private Integer size;
private String batchNo;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

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
        return "Node1{" +
                "data=" + data +
                ", size=" + size +
                '}';
    }
}
