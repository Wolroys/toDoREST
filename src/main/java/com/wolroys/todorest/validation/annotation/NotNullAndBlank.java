package com.wolroys.todorest.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotNull(message = "You must fill this field")
@NotBlank(message = "You must fill this field")
@Constraint(validatedBy = {})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullAndBlank {
    String message() default "This field shouldn't be empty";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
