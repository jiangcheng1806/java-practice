package com.jiangc.strategy;

public class ExternalTaxStrategry implements ChargeStrategy {
    @Override
    public double charge(long cost) {
        return cost;
    }
}
