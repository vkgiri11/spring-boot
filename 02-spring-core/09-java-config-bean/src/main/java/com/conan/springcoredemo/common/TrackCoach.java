package com.conan.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach {
    public TrackCoach() {
        System.out.println("In " + getClass().getSimpleName() + " Constructor");
    }

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }
}
