package com.example.ecommerce.controller;

import com.example.ecommerce.exception.UnauthorizedActionException;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    // POST /orders?username=user
    public Order placeOrder(@RequestParam String username) {
        if (!userService.isUser(username)) {
            throw new UnauthorizedActionException("Only user can place orders");
        }
        return orderService.placeOrder(username);
    }

    // GET /orders?username=user
    @GetMapping
    public List<Order> getOrders(@RequestParam String username) {
        if (!userService.isUser(username)) {
            throw new UnauthorizedActionException("Only user can view orders");
        }
        return orderService.getOrders();
    }
}
