package com.capstone.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class Orders {
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
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "order_product",
            joinColumns = {
                    @JoinColumn(name = "orderID", referencedColumnName = "ID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "productID", referencedColumnName = "ID",
                            nullable = false, updatable = false)})
    private List<Product> products = new ArrayList<>();
    public Orders() {
        productCount = 0;
        totalCost = 0;
    }

    public Orders(int id, int userID, int orderNumber, int productCount, float totalCost, String status) {
        this.id = id;
        this.userID = userID;
        this.orderNumber = orderNumber;
        this.productCount = productCount;
        this.totalCost = totalCost;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}