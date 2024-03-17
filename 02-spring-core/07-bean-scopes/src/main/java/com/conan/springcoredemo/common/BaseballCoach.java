package com.conan.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements  Coach {

    public BaseballCoach() {
        System.out.println("In " + getClass().getSimpleName() + " Constructor");
    }

    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes in batting parctice";
    }
}
