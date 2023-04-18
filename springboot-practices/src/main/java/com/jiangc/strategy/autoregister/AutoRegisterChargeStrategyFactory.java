package com.jiangc.strategy.autoregister;

import com.jiangc.strategy.base.ChargeType;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AutoRegisterChargeStrategyFactory {

    static Map<ChargeType, ChargeStrategy2> chargeStrategyMap = new HashMap<>();

    static {
        autoRegisterTaxStrategy();
    }

    public static ChargeStrategy2 getChargeStrategy(ChargeType taxType) throws Exception {
        if (chargeStrategyMap.containsKey(taxType)){
            return chargeStrategyMap.get(taxType);
        } else {
            throw new Exception("未配置相应策略");
        }
    }

    public static void registerChargeStrategy(ChargeType chargeType, ChargeStrategy2 chargeStrategy2){
        chargeStrategyMap.put(chargeType, chargeStrategy2);
    }

    private static void autoRegisterTaxStrategy(){
        try {
            Reflections reflections = new Reflections(new ConfigurationBuilder()
                    .setUrls(ClasspathHelper.forPackage(ChargeStrategy2.class.getPackage().getName()))
                    .setScanners(new SubTypesScanner()));
            Set<Class<? extends ChargeStrategy2>> taxStrategyClassSet = reflections.getSubTypesOf(ChargeStrategy2.class);

            if (taxStrategyClassSet != null){
                for (Class<?> clazz : taxStrategyClassSet){
                    ChargeStrategy2 chargeStrategy2 = (ChargeStrategy2) clazz.newInstance();
                    chargeStrategy2.register();
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
