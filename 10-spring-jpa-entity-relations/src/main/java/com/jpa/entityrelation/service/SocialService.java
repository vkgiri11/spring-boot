package com.jpa.entityrelation.service;

import com.jpa.entityrelation.model.SocialUser;
import com.jpa.entityrelation.repositories.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialService {
    public Object deleteSocialUser;
    @Autowired
    SocialUserRepository socialUserRepository;

    public List<SocialUser> getSocialUsers() {
        return socialUserRepository.findAll();
    }

    public SocialUser createSocialUser(SocialUser socialUser) {
        return socialUserRepository.save(socialUser);
    }

    public SocialUser deleteSocialUser(Long id) {
        SocialUser socialUser = socialUserRepository.findById(id).orElseThrow(() -> new RuntimeException("Social User Not Found"));
        socialUserRepository.delete(socialUser);
        return socialUser;
    }
}
