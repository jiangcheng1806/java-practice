package com.jiangcz.application.jdk.bigdecimal;

import com.fasterxml.jackson.databind.JsonNode;
import com.jiangcz.application.common.util.JacksonUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal d1 = new BigDecimal(1.23);
        BigDecimal d2 = new BigDecimal("1.23");
        BigDecimal d3 = new BigDecimal(1.23);

        System.out.println("d1>>>>>>>>>"+d1.toString() +",d2>>>>>>>>"+d2.toString() );
        System.out.println("d1>>>>>>>>>"+d1.toString() +",d3>>>>>>>>"+d3.toString() );

        System.out.println(">>>>>>>>>>>>>>>>>d1 = d2 :"+(d1 == d2));
        System.out.println(">>>>>>>>>>>>>>>>>d1 = d3 :"+(d1 == d3));
        System.out.println(">>>>>>>>>>>>>>>>>d1 equals d2 :"+(d1.equals(d2)));
        System.out.println(">>>>>>>>>>>>>>>>>d1 equals d3 :"+(d1.equals(d3)));
        System.out.println(">>>>>>>>>>>>>>>>>d1 compare d2 :"+(d1.compareTo(d2)));
        System.out.println(">>>>>>>>>>>>>>>>>d1 compare d3 :"+(d1.compareTo(d3)));

        String result = "{\"status\":\"S\",\"msg\":null,\"count\":1,\"data\":{\"total\":34.0,\"strList\":[\"321692\"]}}";
        JsonNode data = JacksonUtils.readAnyNode(result, "data");
        ElementVo elementVo = JacksonUtils.parseObject(JacksonUtils.toJSONString(data),ElementVo.class);

        System.out.println("elementVo>>>>>>>>>>>"+JacksonUtils.toJSONString(elementVo));


        System.out.println("mal1>>>>"+elementVo.getTotal());

        System.out.println("mal2>>>>"+new BigDecimal(34.0));

        System.out.println(elementVo.getTotal().equals(new BigDecimal(34.0)));

        List<AccVo> accVoList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AccVo accVo = new AccVo();
            accVo.setVal1(new BigDecimal(i+1.23));
            accVo.setVal2(new BigDecimal(i+1.23+""));
            accVoList.add(accVo);
        }

        BigDecimal val1 = accVoList.stream().map(accVo -> accVo.getVal1()).reduce(BigDecimal.ZERO,BigDecimal::add);
        System.out.println(val1);

        BigDecimal val2 = accVoList.stream().map(accVo -> accVo.getVal2()).reduce(BigDecimal.ZERO,BigDecimal::add);
        System.out.println(val2);

        System.out.println(val1.equals(val2));

    }
}
