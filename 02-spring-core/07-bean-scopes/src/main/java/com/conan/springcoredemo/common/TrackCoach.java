package com.conan.springcoredemo.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TrackCoach implements Coach {
    public TrackCoach() {
        System.out.println("In " + getClass().getSimpleName() + " Constructor");
    }

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }
}
