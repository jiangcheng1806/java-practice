package com.jiangc.strategy.strategymap;

import com.jiangc.strategy.base.ChargeStrategy;
import com.jiangc.strategy.base.ChargeType;
import com.jiangc.strategy.base.ExternalTaxStrategry;
import com.jiangc.strategy.base.InternalStrategry;

import java.util.HashMap;
import java.util.Map;

public class MapChargeStrategyFactory {

    static Map<ChargeType, ChargeStrategy> chargeStrategyMap = new HashMap<>();

    static {
        registerChargeStrategry(ChargeType.INTERNAL,new InternalStrategry());
        registerChargeStrategry(ChargeType.EXTERNAL,new ExternalTaxStrategry());
    }

    public static void registerChargeStrategry(ChargeType taxType,ChargeStrategy taxStrategry){
        chargeStrategyMap.put(taxType,taxStrategry);
    }

    public static ChargeStrategy getChargeStrategry(ChargeType taxType) throws Exception {
        if (chargeStrategyMap.containsKey(taxType)){
            return chargeStrategyMap.get(taxType);
        } else {
            throw new Exception("未配置响应策略");
        }
    }
}
