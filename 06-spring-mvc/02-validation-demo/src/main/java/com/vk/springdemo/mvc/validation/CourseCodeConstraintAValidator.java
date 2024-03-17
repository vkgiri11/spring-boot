package com.vk.springdemo.mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//                                                                  our annotation, type of data to validate
public class CourseCodeConstraintAValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    @Override
    public void initialize(CourseCode theCourseCode) {
        coursePrefix = theCourseCode.value();
    }

    // Spring MVC will call the isValid() function on the runtime
    @Override
    // HTML Form data entered by the user, helper class where we can place additional error messages here
    public boolean isValid(String s, ConstraintValidatorContext theConstraintValidatorContext) {

        boolean result;

        // test if the form data starts with our course prefix
        if (s != null) {
            result = s.startsWith(coursePrefix);
        } else {
            result = true;
        }

        return result;
    }
}
