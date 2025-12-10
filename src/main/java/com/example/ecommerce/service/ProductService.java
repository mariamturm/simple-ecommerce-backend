package com.example.ecommerce.service;

import com.example.ecommerce.exception.ProductNotFoundException;
import com.example.ecommerce.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private Map<Long, Product> products = new HashMap<>();

    private long nextId = 1L;

    @PostConstruct
    public void initProducts(){
        createProduct(new Product(null, "Laptop", 500.0, 10));
        createProduct(new Product(null, "Headphones", 100.0, 20));
        createProduct(new Product(null, "Phone", 800.0, 5));
    }

    public List<Product> getAllProducts(){
        return new ArrayList<>(products.values());
    }

    public Product getProductById(Long id){
        Product product = products.get(id);
        if(product == null){
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        return product;
    }

    public Product createProduct(Product product){
        product.setId(nextId++);
        products.put(product.getId(), product);
        return product;
    }

    public Product updateProduct(Long id, Product updated){
        Product existing = getProductById(id);
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        existing.setStockQuantity(updated.getStockQuantity());
        return existing;
    }

    public void deleteProduct(Long id){
        if(!products.containsKey(id)){
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        products.remove(id);
    }

    public boolean hasEnoughStock(Long productId, int quantity){
        Product p = getProductById(productId);
        return p.getStockQuantity() >= quantity;
    }

    public void reduceStock(Long productId, int quantity){
        Product p = getProductById(productId);
        int newStock = p.getStockQuantity() - quantity;
        if(newStock < 0){
            throw new RuntimeException("Stock not enough");
        }
        p.setStockQuantity(newStock);
    }


}
