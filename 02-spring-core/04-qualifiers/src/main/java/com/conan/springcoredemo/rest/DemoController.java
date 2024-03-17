package com.conan.springcoredemo.rest;

import com.conan.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // define a private field for the dependency
    private Coach myCoach;

    // Constructor
    // the bean id is the same name as class but the first letter is lowercase
    @Autowired
    public DemoController(@Qualifier("baseballCoach") Coach theCoach) {
        myCoach = theCoach;
    }

    // setter
    //@Autowired
    //public void setCoach(@Qualifier("trackCoach") Coach theCoach) {
    //    myCoach = theCoach;
    //}

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
