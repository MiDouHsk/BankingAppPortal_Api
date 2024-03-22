package com.bank_v2.bankingportal_api.anotation;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = EmployMidoriAutoGenerate.class)
public @interface MidouAutoGenerate {
    boolean generateToString() default true;
}
