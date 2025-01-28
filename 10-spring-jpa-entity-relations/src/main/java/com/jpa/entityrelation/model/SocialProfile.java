package com.jpa.entityrelation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // use the instant name created in the SocialUser entity
    @OneToOne
    @JsonIgnore
    // this sets the name of the foreign key in the SocialProfile table
    // @JoinColumn(name = "social_user")
    private SocialUser user;

    private String description;

    private void setSocialUser(SocialUser socialUser) {
        this.user = socialUser;

        if (user.getSocialProfile() != this) {
            user.setSocialProfile(this);
        }
    }
}

/*

Instead of having JOIN columns in both the entity and creating foreign keys in both the places,
we instead ask one of the entity to own the Relationship.

The owning entity will have the JoinColumn and in that entity a column of foreign key will be created.
(the above is optional though.....Having JoinColumn or not won't make a difference
the owning side will have mappedBy the other is the owning one)

Non owning side will have the mappedBy attribute defined. No column will be created in this entity
It will be mapped and managed by the socialProfile field.

 */