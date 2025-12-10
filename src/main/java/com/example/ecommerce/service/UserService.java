package com.example.ecommerce.service;


import org.springframework.stereotype.Service;

@Service
public class UserService {

    private double userBudget = 1000.0;

    public boolean isAdmin(String username){
        return "Admin".equalsIgnoreCase(username);
    }

    public boolean isUser(String username){
        return "User".equalsIgnoreCase(username);
    }

    public double getUserBudget() {
        return userBudget;
    }

    public boolean canAfford(double totalPrice){
        return userBudget >= totalPrice;
    }

    public void reduceBudget(double amount){
        userBudget -= amount;
    }


}
