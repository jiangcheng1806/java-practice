package com.jiangcz.application.redis.data1;

import java.io.Serializable;

/**
 * 类名称：Node0<br>
 * 类描述：<br>
 * 创建时间：2019年09月20日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Node0 implements Serializable {
    //车号
    private String vehicleNo;

    //车辆类型：1 危货牵引车、2 危货挂车 、3普货牵引车、4普货挂车
    private Integer vehicleType;

    //运营范围：英文分号分隔
    private String treeIds;

    //版本
    //private Long version;


    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getTreeIds() {
        return treeIds;
    }

    public void setTreeIds(String treeIds) {
        this.treeIds = treeIds;
    }

    @Override
    public String toString() {
        return "Node0{" +
                "vehicleNo='" + vehicleNo + '\'' +
                ", vehicleType=" + vehicleType +
                ", treeIds='" + treeIds + '\'' +
                '}';
    }
}
