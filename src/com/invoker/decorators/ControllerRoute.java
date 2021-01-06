package com.invoker.decorators;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerRoute {
    String value() default "";
}

