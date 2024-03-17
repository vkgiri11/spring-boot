package com.conan.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach {
    public TrackCoach() {
        System.out.println("In " + getClass().getSimpleName() + " Constructor");
    }

    // the init method
    @PostConstruct
    public void doingStartup() {
        System.out.println("In init mmethod: " + getClass().getSimpleName() + " function");
    }

    // the destroy method
    @PreDestroy
    public void doingDestroy() {
        System.out.println("In destroy method: " + getClass().getSimpleName() + " function");
    }


    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }
}
