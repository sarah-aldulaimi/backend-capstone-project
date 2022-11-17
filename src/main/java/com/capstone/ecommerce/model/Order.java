package com.capstone.ecommerce.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;
@Data
@Entity
@Table (name ="orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int id;
    @Column
    int userID;
    @Column
    int orderNumber;
    @Column
    int productCount;
    @Column
    float totalCost;
    @Column
    String status;
    @JsonIgnore
    //using this so that application does not send the roles as part of the json object
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "order_product",
            joinColumns = {
                    @JoinColumn(name = "orderID", referencedColumnName = "ID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "productID", referencedColumnName = "ID",
                            nullable = false, updatable = false)})
    private List<Product> products = new ArrayList<>();

}