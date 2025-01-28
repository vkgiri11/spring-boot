package com.jpa.entityrelation.repositories;

import com.jpa.entityrelation.model.SocialProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialProfileRepository extends JpaRepository<SocialProfile, Long> {
}
