package com.jiangc.strategy.spring_5;

import com.jiangc.strategy.base_1.ChargeType;
import org.springframework.stereotype.Component;

@Component
@ChargeTypeAnnotation2(taxType = ChargeType.INTERNAL)
public class InternalTaxStrategy4 implements ChargeStrategy4 {
    @Override
    public double charge(long cost) {
        final double taxRate = 0.2;
        return cost * taxRate;
    }

}
