package com.vk.springdemo.mvc;

import com.vk.springdemo.mvc.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

    private String firstName;

    @NotNull(message = "This Field is required") // if it is null throw this error message
    @Size(min = 3, message = "The Length should be greater than 3") // if the size is less than 3, throw this error message
    private String lastName;

    @NotNull(message = "This Field is required") // if it is null throw this error message
    @Min(value = 1, message = "Value Should be greater than or equal to 1")
    @Max(value = 10, message = "Value Should be less than or equal to 10")
    private Integer freePasses; // changed from int to Integer so that we don't get Type errors for null values

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "Only 5 characters/digits allowed")
    private String postalCode;

    @CourseCode(value = "AB", message = "Must Start with AB")
    private String courseCode;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }
}
