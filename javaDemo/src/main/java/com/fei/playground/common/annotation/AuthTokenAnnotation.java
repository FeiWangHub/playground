package com.fei.playground.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: zzb
 * @date: 2018/12/20 14:18
 * @description: 自定义注解（是否需要token验证）
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthTokenAnnotation {
    boolean required() default true;
}
