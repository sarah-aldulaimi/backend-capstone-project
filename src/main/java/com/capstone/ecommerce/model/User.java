package com.capstone.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Getter @Setter
    int id;

    @Column
    @Getter @Setter
    String firstName;
    @Column
    @Getter @Setter
    String lastName;
    @Column
    @Getter @Setter
    int age;
    @Column
    @Getter @Setter
    String email;
    @Column
    @Getter @Setter
    String mobile;
    @Column
    @Getter @Setter
    String password;
    @Column
    @Getter @Setter
    int locationId;
    @JsonIgnore
    //using this so that application does not send the roles as part of the json object
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "userID", referencedColumnName = "ID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "roleID", referencedColumnName = "ID",
                            nullable = false, updatable = false)})

    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

}
