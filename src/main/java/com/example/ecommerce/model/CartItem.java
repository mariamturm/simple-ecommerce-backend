package com.example.ecommerce.model;

public class CartItem {

    private Long productId; //using Long instead of long because we need Id to be nullable and with long it is impossible
    //because it's primitive type.

    private String productName;

    //quantity and price never needs to be null, so we can use primitive types
    private int quantity;

    private double price;

    public CartItem() {

    }

    public CartItem(Long productId, String productName, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
