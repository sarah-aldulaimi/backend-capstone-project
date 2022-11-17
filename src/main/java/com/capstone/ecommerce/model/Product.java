package com.capstone.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int id;
    @Column
    String name;
    @Column
    String description;
    @Column
    float price;
    @Column
    int categoryID;

    @JsonIgnore
    //using this so that application does not send the roles as part of the json object
    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();

}
