package com.jpa.entityrelation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "socialGroups")
    @JsonIgnore
    private Set<SocialUser> socialUsers = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

/*

What happens when we use the controller to get Users?

User Entity has id, group. The Group entity has users again

So when printing the JSON this becomes a circular reference..
user data is printing id and groups. The groups is again printing id and users.

This leads to StackOverflowError. So we use JSONIgnore annotation to stop it

so we added JSONIgnore here in groups. Now when we print users

Id is printed, group is printed.
When group id is printed....the users is now ignored in JSON object

 */
