package com.example.expireddatereminder.entity;

public class Product {
    private int id;
    private String productName;
    private String expireDate;

    public Product() {}

    public Product(int id, String productName, String expireDate) {
        this.setId(id);
        this.setProductName(productName);
        this.setExpireDate(expireDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}
