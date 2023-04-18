package com.jiangc.strategy.autoregister_3;

import com.jiangc.strategy.base_1.ChargeType;

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
