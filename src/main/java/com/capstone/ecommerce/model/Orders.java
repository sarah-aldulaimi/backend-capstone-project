package com.capstone.ecommerce.model;

import javax.persistence.*;
import java.util.Random;

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
    int productID;
    @Column
    int productCount;
    @Column
    float totalCost;
    @Column
    String status;

    public Orders() {
    }

    public Orders(int id, int userID, int orderNumber, int productID, int productCount, float totalCost, String status) {
        this.id = id;
        this.userID = userID;
        this.orderNumber = orderNumber;
        this.productID = productID;
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

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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



}