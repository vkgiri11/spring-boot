package com.jpa.entityrelation.repositories;

import com.jpa.entityrelation.model.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserRepository extends JpaRepository<SocialUser, Long> {
}
