package com.jiangc.autoregister;

import com.jiangc.strategy.ChargeType;

public class ExternalTaxStrategy2 implements ChargeStrategy2 {
    @Override
    public double charge(long cost) {
        return cost;
    }

    @Override
    public void register() {
        AutoRegisterChargeStrategyFactory.registerChargeStrategy(ChargeType.EXTERNAL,this);
    }
}
