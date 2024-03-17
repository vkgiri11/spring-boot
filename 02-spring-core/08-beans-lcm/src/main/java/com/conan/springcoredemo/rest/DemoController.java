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

    @Autowired
    public DemoController(@Qualifier("trackCoach") Coach theCoach) {
        System.out.println("In " + getClass().getSimpleName() + " Constructor");
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

}

/*

    Prototype Beans and Destroy Lifecycle ->
        For "prototype" scoped beans, Spring does not call the destroy method.

        In contrast to the other scopes, Spring does not manage the complete lifecycle of a prototype bean:
        the container instantiates, configures, and otherwise assembles a prototype object, and hands it to the client, with no further record of that prototype instance.

        Thus, although initialization lifecycle callback methods are called on all objects regardless of scope,
         in the case of prototypes, configured destruction lifecycle callbacks are not called.
         The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding.



        Prototype Beans and Lazy Initialization ->
        Prototype beans are lazy by default. There is no need to use the @Lazy annotation for prototype scopes beans.

 */