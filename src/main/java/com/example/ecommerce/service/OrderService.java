package com.example.ecommerce.service;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final UserService userService;

    private final CartService cartService;

    private final ProductService productService;

    private List<Order> orders = new ArrayList<>();

    private long nextOrderId = 1L;

    public OrderService(UserService userService, CartService cartService, ProductService productService) {
        this.userService = userService;
        this.cartService = cartService;
        this.productService = productService;

    }

    public Order placeOrder(String username){
        if(!userService.isUser(username)){
            throw new RuntimeException("Only user can place order");
        }
        if(cartService.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double total = cartService.calculateTotal();

        if(!userService.canAfford(total)){
            throw new InsufficientBudgetException("Not enough budget to complete the order.");
        }

        for(CartItem item : cartService.getCart().getItems()){
            if(!productService.hasEnoughStock(item.getProductId(), item.getQuantity())){
                throw new InsuffiecientStockException("Not enough stock for the product: " + item.getProductName());
            }
        }

        //Update Stock
        for(CartItem item : cartService.getCart().getItems()){
            productService.reduceStock(item.getProductId(), item.getQuantity());
        }

        userService.reduceBudget(total);

        //Cpy cart into order
        List<CartItem> orderItems = new ArrayList<>();
        for(CartItem item : cartService.getCart().getItems()){
            orderItems.add(new CartItem(item.getProductId(), item.getProductName(), item.getPrice(), item.getQuantity()));

        }
        Order order = new Order(nextOrderId++, orderItems, total, LocalDateTime.now());

        orders.add(order);
        cartService.clearCart();
        return order;
    }

    public List<Order> getOrders() {
        return orders;
    }

}
