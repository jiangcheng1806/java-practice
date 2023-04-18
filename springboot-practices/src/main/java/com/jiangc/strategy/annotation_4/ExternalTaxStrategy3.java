package com.jiangc.strategy.annotation_4;

import com.jiangc.strategy.base_1.ChargeType;

@ChargeTypeAnnotation(taxType = ChargeType.EXTERNAL)
public class ExternalTaxStrategy3 implements ChargeStrategy3 {
    @Override
    public double charge(long cost) {
        return cost;
    }
}
