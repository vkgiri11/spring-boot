package com.conan.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {
    public CricketCoach() {
        System.out.println("In " + getClass().getSimpleName() + " Constructor");
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 25 minutes";
    }
}
