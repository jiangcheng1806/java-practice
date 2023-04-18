package com.jiangc.strategy.annotation;

import com.jiangc.strategy.base.ChargeType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ChargeTypeAnnotation {
    ChargeType taxType();
}
