package com.jiangcz.application.jdk.annotation.simpleorm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义字段注解,完成类属性和表字段的映射
 */
@Retention(RetentionPolicy.RUNTIME) //要反射,故注解信息需要保留到运行期
@Target(ElementType.FIELD) //只能用在类属性上
public @interface MyColumn {
    //列名
    String value();

    //字段类型,默认为字符串类型
    String type() default "VARCHAR(30)";//字段类型,默认为VARCHAR类型

    //类型为注解类型的字段约束,默认的约束为:非主键，非唯一字段，不能为null
    Constraints constraint() default @Constraints;
}
