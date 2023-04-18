package com.jiangc.strategy.annotation;

import com.jiangc.strategy.base.ChargeType;

@ChargeTypeAnnotation(taxType = ChargeType.EXTERNAL)
public class ExternalTaxStrategy3 implements ChargeStrategy3 {
    @Override
    public double charge(long cost) {
        return cost;
    }
}
