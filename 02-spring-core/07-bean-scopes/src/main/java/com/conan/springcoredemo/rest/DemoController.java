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
    private Coach anotherCoach;

    // default bean scope is singelton, all dependency injection refers to the same bean
    @Autowired
    public DemoController(@Qualifier("trackCoach") Coach theCoach, @Qualifier("trackCoach") Coach theAnotherCoach) {
        System.out.println("In " + getClass().getSimpleName() + " Constructor");
        myCoach = theCoach;
        anotherCoach = theAnotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/checkBeanScope")
    public String compareBeans() {
        return "Comparing beans: myCoach & another Coach, " + (myCoach == anotherCoach ? "Singelton" : "Prototype");
    }

}
