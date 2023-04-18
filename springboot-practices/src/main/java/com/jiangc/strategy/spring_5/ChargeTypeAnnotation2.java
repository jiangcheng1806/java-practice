package com.jiangc.strategy.spring_5;

import com.jiangc.strategy.base_1.ChargeType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ChargeTypeAnnotation2 {
    ChargeType taxType();
}
