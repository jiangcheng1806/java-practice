package com.jiangc.strategy.base_1;

import org.springframework.stereotype.Component;

@Component
public class ChargeStrategryFactory {

    public static ChargeStrategy getChargeStrategy(ChargeType taxType) throws Exception {
        if (taxType == ChargeType.INTERNAL){
            return new InternalStrategry();
        } else if (taxType == ChargeType.EXTERNAL) {
            return new ExternalTaxStrategry();
        } else {
            throw new Exception("未配置响应策略");
        }
    }
}
