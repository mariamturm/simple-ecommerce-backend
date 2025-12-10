package com.example.ecommerce.controller;

import com.example.ecommerce.exception.UnauthorizedActionException;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    private final UserService userService;

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    //GET /products?username=admin
    //GET /products?username=user
    @GetMapping
    public List<Product> getAllProducts(@RequestParam String username) {
        //both admin and user can view
        return productService.getAllProducts();
    }

    @GetMapping
    public Product getProduct(@PathVariable Long id, @RequestParam String username) {
        return productService.getProductById(id);
    }

    //POST /products?username=admin
    @PostMapping
    public Product createProduct(@RequestParam String username, @RequestBody Product product) {
        if(!userService.isAdmin(username)){
            throw new UnauthorizedActionException("Only admins can create products");
        }
        return productService.createProduct(product);
    }

    //PUT /products/1?username=admin
    @PutMapping("/{id}")
    public Product updateProduct(@RequestParam String username, @PathVariable Long id, @RequestBody Product product) {
        if(!userService.isAdmin(username)){
            throw new UnauthorizedActionException("Only admins can update products");
        }
        return productService.updateProduct(id, product);
    }

    //DELETE /products/1?username=admin
    @DeleteMapping("/{id}")
    public void deleteProduct(@RequestParam String username, @PathVariable Long id){
        if(!userService.isAdmin(username)){
            throw new UnauthorizedActionException("Only admins can delete products");
        }
        productService.deleteProduct(id);
    }


}
