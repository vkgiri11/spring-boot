package com.conan.springcoredemo.config;

import com.conan.springcoredemo.common.Coach;
import com.conan.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    // the bean id defaults to the method name
    //@Bean
    //public Coach swimCoach() {
    //    return new SwimCoach();
    //}
    //

    // custom bean id
    @Bean("aquatic")
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
