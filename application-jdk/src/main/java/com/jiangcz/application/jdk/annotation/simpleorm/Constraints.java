package com.jiangcz.application.jdk.annotation.simpleorm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 约束注解:主键,是否为空,是否唯一等信息。
 */
@Retention(RetentionPolicy.RUNTIME) //运行期
@Target(ElementType.FIELD) //只能在类属性上使用
public @interface Constraints {
    //字段是否为主键约束
    boolean primaryKey() default false;

    //字段是否允许为null
    boolean nullable() default false;

    //字段是否唯一
    boolean unique() default false;
}
