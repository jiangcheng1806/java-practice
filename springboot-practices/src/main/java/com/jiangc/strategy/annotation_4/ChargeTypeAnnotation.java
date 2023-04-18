package com.jiangc.strategy.annotation_4;

import com.jiangc.strategy.base_1.ChargeType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ChargeTypeAnnotation {
    ChargeType taxType();
}
