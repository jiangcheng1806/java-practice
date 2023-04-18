package com.jiangc.strategy.spring_5;

import com.jiangc.strategy.annotation_4.ChargeStrategy3;
import com.jiangc.strategy.annotation_4.ChargeTypeAnnotation;
import com.jiangc.strategy.base_1.ChargeType;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationChargeStrategyFactory2 {

    static Map<ChargeType, ChargeStrategy4> chargeStrategyMap = new HashMap<>();

    static {
        registerChargetStrategy();
    }

    public static ChargeStrategy4 getChargeStrategy(ChargeType chargeType) throws Exception {
        if (chargeStrategyMap.containsKey(chargeType)){
            return chargeStrategyMap.get(chargeType);
        } else {
            throw new Exception("未配置相应策略");
        }
    }

    private static void registerChargetStrategy(){
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(ChargeStrategy4.class.getPackage().getName()))
                .setScanners(new SubTypesScanner()));

        Set<Class<? extends ChargeStrategy4>> taxStrategyClassSet = reflections.getSubTypesOf(ChargeStrategy4.class);
        if (taxStrategyClassSet != null){
            for (Class<?> clazz : taxStrategyClassSet){
                if (clazz.isAnnotationPresent(ChargeTypeAnnotation2.class)){
                    ChargeTypeAnnotation2 taxTypeAnnotation = clazz.getAnnotation(ChargeTypeAnnotation2.class);
                    ChargeType chargeType = taxTypeAnnotation.taxType();
                    chargeStrategyMap.put(chargeType,(ChargeStrategy4) ApplicationContextHelper.popBean(clazz));
                }
            }
        }
    }
}
