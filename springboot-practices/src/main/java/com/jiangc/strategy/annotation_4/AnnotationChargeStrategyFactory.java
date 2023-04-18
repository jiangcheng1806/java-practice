package com.jiangc.strategy.annotation_4;

import com.jiangc.strategy.base_1.ChargeType;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationChargeStrategyFactory {

    static Map<ChargeType,ChargeStrategy3> chargeStrategyMap = new HashMap<>();

    static {
registerChargeStrategy();
    }

    public static ChargeStrategy3 getChargeStrategy(ChargeType chargeType) throws Exception {
        if (chargeStrategyMap.containsKey(chargeType)){
            return chargeStrategyMap.get(chargeType);
        } else {
            throw new Exception("未配置相应策略");
        }
    }

    private static void registerChargeStrategy(){
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(ChargeStrategy3.class.getPackage().getName()))
                .setScanners(new SubTypesScanner()));

        Set<Class<? extends ChargeStrategy3>> taxStrategyClassSet = reflections.getSubTypesOf(ChargeStrategy3.class);

        if (taxStrategyClassSet != null){
            for (Class<?> clazz : taxStrategyClassSet){
                if (clazz.isAnnotationPresent(ChargeTypeAnnotation.class)){
                    ChargeTypeAnnotation taxTypeAnnotation = clazz.getAnnotation(ChargeTypeAnnotation.class);
                    ChargeType chargeType = taxTypeAnnotation.taxType();
                    try {
                        chargeStrategyMap.put(chargeType,(ChargeStrategy3)clazz.newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
