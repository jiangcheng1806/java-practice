package com.jiangc.autoregister;

import com.jiangc.strategy.ChargeType;

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
