package com.example.ecommerce.service;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.ShoppingCart;
import com.example.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Iterator;


@Service
public class CartService {

    private final ProductService productService;

    private ShoppingCart cart = new ShoppingCart();

    public CartService(ProductService productService) {
        this.productService = productService;
    }

    public void addToCart(Long productId, int quantity){
        Product product = productService.getProductById(productId);

        if(!productService.hasEnoughStock(productId, quantity)){
            throw new InsufficientStockExceprion("Not enough stock for product: " + product.getName());
        }

        for(CartItem item : cart.getItems()){
            if(item.getProductId().equals(productId)){
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        CartItem newItem = new CartItem(product.getId(), product.getName(), product.getPrice(), quantity);

        cart.addItem(newItem);
    }

    public void removeFromCart(Long productId){
        Iterator<CartItem> iterator = cart.getItems().iterator();
        while(iterator.hasNext()){
            CartItem item = iterator.next();
            if(item.getProductId().equals(productId)){
                iterator.remove();
                break;
            }
        }
    }

    public ShoppingCart getCart(){
        return cart;
    }

    public double calculateTotal(){
        double total = 0;
        for(CartItem item : cart.getItems()){
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public void clearCart(){
        cart.clear();
    }

    public boolean isEmpty(){
        return cart.getItems().isEmpty();
    }





}
