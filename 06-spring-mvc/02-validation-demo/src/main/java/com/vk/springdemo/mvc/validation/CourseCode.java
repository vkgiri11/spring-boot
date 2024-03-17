package com.vk.springdemo.mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintAValidator.class) // helper class that contains the actual validation logic
@Target({ElementType.METHOD, ElementType.FIELD}) // can apply our annotation to a method or a field
@Retention(RetentionPolicy.RUNTIME) // Retain this annotation in the Java class file(bytecode), Process it at runtime by the JVM
public @interface CourseCode {

    // define default course code
    public String value() default "VK"; // if no value in passed in "value" take the default VK

    // define default error message
    public String message() default "must start with VK"; // if no value in passed in message take the default

    // define default groups
    public  Class<?>[] groups() default {}; // Groups: can group related constraints

    // define default payloads
    public Class<? extends Payload>[] payload() default {}; // Payloads: provide custom details about validation failure (severity level, error code etc.)
}
