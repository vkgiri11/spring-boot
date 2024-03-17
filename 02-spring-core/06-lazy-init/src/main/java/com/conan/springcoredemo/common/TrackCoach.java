package com.conan.springcoredemo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy // not created until this bean is needed
public class TrackCoach implements Coach {
    public TrackCoach() {
        System.out.println("In " + getClass().getSimpleName() + " Constructor");
    }

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }
}
