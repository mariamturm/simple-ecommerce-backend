package com.example.ecommerce.model;

import java.time.LocalDateTime;
import java.util.List;

//represents completed purchase
public class Order {

    private Long id; //unique identifier for an order

    private List<CartItem> items; //stores all the items that were purchased in this order

    private double totalPrice;

    private LocalDateTime createdAt;

    public Order() {

    }

    public Order(Long id, List<CartItem> items, double totalPrice, LocalDateTime createdAt) {
        this.id = id;
        this.items = items;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
