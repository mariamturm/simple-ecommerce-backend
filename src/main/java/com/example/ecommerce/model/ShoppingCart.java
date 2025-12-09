package com.example.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) { //allows replacing the entire cart with new list.
        //maybe for when restoring from database or resetting
        this.items = items;
    }

    public void addItem(CartItem item){
        items.add(item);
    }

    public void clear(){ //this clears list empty
        items.clear();
    }
}
