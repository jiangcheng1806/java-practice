package com.jiangc.strategy.spring_5;

import com.jiangc.strategy.base_1.ChargeType;
import org.springframework.stereotype.Component;

@Component
@ChargeTypeAnnotation2(taxType = ChargeType.EXTERNAL)
public class ExternalTaxStrategy4 implements ChargeStrategy4 {
    @Override
    public double charge(long cost) {
        return cost;
    }
}
