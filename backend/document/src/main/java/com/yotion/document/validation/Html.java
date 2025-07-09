package com.yotion.document.validation;

/**
 * File is created by andreychernenko at 09.07.2025
 */

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = HtmlValidator.class)
public @interface Html {

    String message() default "Invalid HTML";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
