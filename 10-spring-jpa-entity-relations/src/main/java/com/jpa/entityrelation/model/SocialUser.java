package com.jpa.entityrelation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // now since OneToOne is added in both the entities it becomes a bi-directional mapping
    // if we save a user, we don't have the social profile set yet. so this throws an error
    // hence we need to CASCADE the operations so that both the profile and user is created simultaneously
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private SocialProfile socialProfile;

    @OneToMany(mappedBy = "socialUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<SocialGroup> socialGroups = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // this is needed to maintain consistency of the bi-directional relationship of both the entities
    // when we set a user and it's profile, we need to ensure that in the profile the user is updated too
    public void setSocialProfile(SocialProfile socialProfile) {
        socialProfile.setUser(this);
        this.socialProfile = socialProfile;
    }

}

/*
In ManyToOne and OneToMany generally we have a extra table created which keeps
a track of foreign keys and have the relationships between tables there.

But here we have mappedBy in the posts. So POST entity is owning the relationship
and no extra table will be created.
*/

/*
 JoinTable will create a new table, to story many-to-many relationships
  the name of this table can be specified. The
*/
