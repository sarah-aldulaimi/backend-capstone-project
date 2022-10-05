package com.capstone.ecommerce.model;

import javax.persistence.*;
@Entity
@Table
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int id;
    @Column
    String firstName;
    @Column
    String lastName;
    @Column
    int age;
    @Column
    String email;
    @Column
    String mobile;
    @Column
    String password;
    @Column
    int locationId;

    public Users(){
    }
    public Users(int id, String firstName, String lastName, int age, String email, String mobile, String password, int locId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.locationId = locId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locaId) {
        this.locationId = locaId;
    }
}
