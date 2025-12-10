package com.example.ecommerce.controller;

import com.example.ecommerce.exception.UnauthorizedActionException;
import com.example.ecommerce.model.ShoppingCart;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    // POST /cart/add?username=user&productId=1&quantity=2
    @PostMapping("/add")
    public ShoppingCart addToCart(@RequestParam String username,
                                  @RequestParam Long productId,
                                  @RequestParam int quantity) {

        if (!userService.isUser(username)) {
            throw new UnauthorizedActionException("Only user can manage cart");
        }

        cartService.addToCart(productId, quantity);
        return cartService.getCart();
    }

    // POST /cart/remove?username=user&productId=1
    @PostMapping("/remove")
    public ShoppingCart removeFromCart(@RequestParam String username,
                                       @RequestParam Long productId) {

        if (!userService.isUser(username)) {
            throw new UnauthorizedActionException("Only user can manage cart");
        }

        cartService.removeFromCart(productId);
        return cartService.getCart();
    }

    // GET /cart?username=user
    @GetMapping
    public ShoppingCart getCart(@RequestParam String username) {
        if (!userService.isUser(username)) {
            throw new UnauthorizedActionException("Only user can view cart");
        }
        return cartService.getCart();
    }

    // GET /cart/total?username=user
    @GetMapping("/total")
    public double getTotal(@RequestParam String username) {
        if (!userService.isUser(username)) {
            throw new UnauthorizedActionException("Only user can view cart");
        }
        return cartService.calculateTotal();
    }
}

