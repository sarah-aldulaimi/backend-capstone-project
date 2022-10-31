package com.capstone.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Getter @Setter
    int id;
    @Column
    @Getter @Setter
    String name;
    @Column
    @Getter @Setter
    String description;

    @JsonIgnore
    //using this so that application does not send the roles as part of the json object
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)


    private Set<User> users = new HashSet<>();
    public Set<User> getUsers() {
        return users;
    }

}
