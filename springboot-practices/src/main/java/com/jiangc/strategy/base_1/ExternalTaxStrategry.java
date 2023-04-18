package com.jiangc.strategy.base_1;

public class ExternalTaxStrategry implements ChargeStrategy {
    @Override
    public double charge(long cost) {
        return cost;
    }
}
