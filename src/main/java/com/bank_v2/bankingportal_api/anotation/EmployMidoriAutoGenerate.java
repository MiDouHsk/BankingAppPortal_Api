package com.bank_v2.bankingportal_api.anotation;

import com.bank_v2.bankingportal_api.anotation.MidouAutoGenerate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class EmployMidoriAutoGenerate implements ConstraintValidator<MidouAutoGenerate, String> {
    @Override
    public void initialize(MidouAutoGenerate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }

    public static void autoGenerateGetterAndSetters(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            String fieldName = field.getName();
            String capitalizedFieldName = fieldName.substring(0, 1). toUpperCase() + fieldName.substring(1);
            try {
                clazz.getMethod("set" + capitalizedFieldName, field.getType()).invoke(obj, null);
                clazz.getMethod("get" + capitalizedFieldName).invoke(obj);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        if (clazz.isAnnotationPresent(MidouAutoGenerate.class)) {
            MidouAutoGenerate annotation = clazz.getAnnotation(MidouAutoGenerate.class);
            if (annotation.generateToString()) {
                generateToString(obj, clazz);
            }
        }
    }

    private static void generateToString(Object obj, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append(clazz.getSimpleName()).append(" [");
        for (Field field : fields) {
            String fieldName = field.getName();
            String capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Object value = clazz.getMethod("get" + capitalizedFieldName).invoke(obj);
                sb.append(fieldName).append(": ").append(value).append(", ");
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        System.out.println(sb.toString());
    }
}
