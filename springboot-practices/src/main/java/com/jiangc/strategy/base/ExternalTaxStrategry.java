package com.jiangc.strategy.base;

public class ExternalTaxStrategry implements ChargeStrategy {
    @Override
    public double charge(long cost) {
        return cost;
    }
}
