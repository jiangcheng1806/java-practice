package com.jiangcz.application.redis.data0;

import java.io.Serializable;

/**
 * 类名称：com.jiangcz.application.http.data0.Node0<br>
 * 类描述：<br>
 * 创建时间：2019年09月19日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Node0 implements Serializable {
    private String cardNo;
    private String driverName;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        return "com.jiangcz.application.http.data0.Node0{" +
                "cardNo='" + cardNo + '\'' +
                ", driverName='" + driverName + '\'' +
                '}';
    }
}
