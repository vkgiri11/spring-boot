package com.jpa.entityrelation.controller;

import com.jpa.entityrelation.model.SocialUser;
import com.jpa.entityrelation.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocialController {
    @Autowired
    private SocialService socialService;

    @GetMapping("/social/users")
    public ResponseEntity<List<SocialUser>> getSocialUsers() {
        return new ResponseEntity<>(socialService.getSocialUsers(), HttpStatus.OK);
    }

    @PostMapping("/social/users")
    public ResponseEntity<SocialUser> createSocialUser(@RequestBody SocialUser socialUser) {
        return new ResponseEntity<>(socialService.createSocialUser(socialUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/social/users/{id}")
    public ResponseEntity<String> deleteSocialUser(@PathVariable Long id) {
        socialService.deleteSocialUser(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

}
