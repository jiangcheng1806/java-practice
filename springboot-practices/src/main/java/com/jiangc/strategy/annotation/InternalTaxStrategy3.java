package com.jiangc.strategy.annotation;

import com.jiangc.strategy.base.ChargeType;

@ChargeTypeAnnotation(taxType = ChargeType.INTERNAL)
public class InternalTaxStrategy3 implements ChargeStrategy3 {
    @Override
    public double charge(long cost) {
        final double taxRate = 0.2;
        return cost * taxRate;
    }

}
