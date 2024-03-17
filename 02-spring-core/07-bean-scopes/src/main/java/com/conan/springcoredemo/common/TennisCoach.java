package com.conan.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {
    public TennisCoach() {
        System.out.println("In " + getClass().getSimpleName() + " Constructor");
    }
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}
