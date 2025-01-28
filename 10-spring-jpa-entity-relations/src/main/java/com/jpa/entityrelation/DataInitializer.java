package com.jpa.entityrelation;

import com.jpa.entityrelation.model.Post;
import com.jpa.entityrelation.model.SocialGroup;
import com.jpa.entityrelation.model.SocialProfile;
import com.jpa.entityrelation.model.SocialUser;
import com.jpa.entityrelation.repositories.PostRepository;
import com.jpa.entityrelation.repositories.SocialGroupRepository;
import com.jpa.entityrelation.repositories.SocialProfileRepository;
import com.jpa.entityrelation.repositories.SocialUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    private final SocialUserRepository socialUserRepository;
    private final SocialGroupRepository socialGroupRepository;
    private final SocialProfileRepository socialProfileRepository;
    private final PostRepository postRepository;

    public DataInitializer(PostRepository postRepository, SocialUserRepository socialUserRepository, SocialGroupRepository socialGroupRepository, SocialProfileRepository socialProfileRepository) {
        this.postRepository = postRepository;
        this.socialUserRepository = socialUserRepository;
        this.socialGroupRepository = socialGroupRepository;
        this.socialProfileRepository = socialProfileRepository;
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            SocialUser socialUser1 = new SocialUser();
            SocialUser socialUser2 = new SocialUser();
            SocialUser socialUser3 = new SocialUser();
            socialUserRepository.save(socialUser1);
            socialUserRepository.save(socialUser2);
            socialUserRepository.save(socialUser3);

            SocialGroup socialGroup1 = new SocialGroup();
            SocialGroup socialGroup2 = new SocialGroup();

            // add users to groups
            socialGroup1.getSocialUsers().add(socialUser1);
            socialGroup1.getSocialUsers().add(socialUser2);

            socialGroup2.getSocialUsers().add(socialUser2);
            socialGroup2.getSocialUsers().add(socialUser3);

            // associate users with groups
            socialUser1.getSocialGroups().add(socialGroup1);
            socialUser2.getSocialGroups().add(socialGroup1);
            socialUser2.getSocialGroups().add(socialGroup2);
            socialUser3.getSocialGroups().add(socialGroup2);

            // save the groups
            socialGroupRepository.save(socialGroup1);
            socialGroupRepository.save(socialGroup2);

            // now save users again to database, since we added groups recently
            socialUserRepository.save(socialUser1);
            socialUserRepository.save(socialUser2);
            socialUserRepository.save(socialUser3);


            Post post1 = new Post();
            Post post2 = new Post();
            Post post3 = new Post();

            // associate posts with users
            post1.setSocialUser(socialUser1);
            post2.setSocialUser(socialUser2);
            post3.setSocialUser(socialUser3);

            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);

            SocialProfile socialProfile1 = new SocialProfile();
            SocialProfile socialProfile2 = new SocialProfile();
            SocialProfile socialProfile3 = new SocialProfile();

            // associate profiles with users
            socialProfile1.setUser(socialUser1);
            socialProfile2.setUser(socialUser2);
            socialProfile3.setUser(socialUser3);

            socialProfileRepository.save(socialProfile1);
            socialProfileRepository.save(socialProfile2);
            socialProfileRepository.save(socialProfile3);

            // FETCH TYPES
            System.out.println("FETCHING SOCIAL USERS");
            socialUserRepository.findById(1L);
        };
    }

}
