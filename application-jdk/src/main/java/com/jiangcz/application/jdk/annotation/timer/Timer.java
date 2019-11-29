package com.jiangcz.application.jdk.annotation.timer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类名称：Timer<br>
 * 类描述：<br>
 * 创建时间：2018年08月25日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Timer {
}
