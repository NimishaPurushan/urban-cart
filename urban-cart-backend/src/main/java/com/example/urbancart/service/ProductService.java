package com.example.urbancart.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.urbancart.repository.ProductRepository;
import com.example.urbancart.model.Product;

@Service
public class ProductService {

    public final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public String addProduct(Product product) {
        this.productRepository.save(product);
        return "Product added successfully: " + product.getName() + " - Price: " + product.getPrice();
    }

    public String updateProduct(String name, double price, String description) {
        return "Product updated successfully: " + name + " - Price: " + price;
    }

    
}
