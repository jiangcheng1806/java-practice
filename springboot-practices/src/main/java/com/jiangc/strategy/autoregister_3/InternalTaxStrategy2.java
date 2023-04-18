package com.jiangc.strategy.autoregister_3;

import com.jiangc.strategy.base_1.ChargeType;

public class InternalTaxStrategy2 implements ChargeStrategy2 {
    @Override
    public double charge(long cost) {
        final double taxRate = 0.2;
        return cost * taxRate;
    }

    @Override
    public void register() {
        AutoRegisterChargeStrategyFactory.registerChargeStrategy(ChargeType.INTERNAL,this);
    }
}
