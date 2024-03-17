package com.conan.springcoredemo.common;

public class SwimCoach implements Coach {

    public SwimCoach() {
        System.out.println("In " + getClass().getSimpleName() + " Constructor");
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 7 laps as warmup";
    }
}
