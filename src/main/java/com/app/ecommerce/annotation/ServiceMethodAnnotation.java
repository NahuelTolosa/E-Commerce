package com.app.ecommerce.annotation;

import java.lang.annotation.*;

@Documented
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceMethodAnnotation {
}
