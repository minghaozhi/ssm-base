package com.ssm.Log;

import java.lang.annotation.*;

/**
 * 自定义注解拦截Controller

 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {
	String description() default "";
}
