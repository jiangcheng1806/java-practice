package com.jiangcz.application.jdk.bigdecimal;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ElementVo {

    /**
     * 自定义单号集合
     */
    private List<String> strList;

    /**
     * 总量
     */
    private BigDecimal total;
}
