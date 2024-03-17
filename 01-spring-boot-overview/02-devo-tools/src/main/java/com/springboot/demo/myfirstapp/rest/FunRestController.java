package com.springboot.demo.myfirstapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/workout")
    public String workout() {
        return "Working out Dev tools";
    }

    @GetMapping("/booyah")
    public String wosayBooyahrkout() {
        return "Boooooyaaaaahhh.....";
    }
}
