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

    // since the TennisCoach bean is defined as Primary, that will be implemented
    // but Qualifier has greater priority than Primary, so if I had
    // Qualifier with beanid: TrackCoach, that will be used

    @Autowired
    public DemoController(Coach theCoach) {
        myCoach = theCoach;
    }

    //@Autowired
    //public DemoController(@Qualifier("trackCoach") Coach theCoach) {
    //    myCoach = theCoach;
    //}

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
