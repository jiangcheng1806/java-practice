package com.jiangc.strategy.autoregister;

import com.jiangc.strategy.base.ChargeType;

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
