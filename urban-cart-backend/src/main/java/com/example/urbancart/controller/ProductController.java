package com.example.urbancart.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.urbancart.model.Product;
import com.example.urbancart.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController{

    public final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody Product product) {
        this.productService.addProduct(product);
        return "Product added successfully: " + product.getName() + " - Price: " + product.getPrice();
    }

    @PostMapping("/update")
    public String updateProduct(@RequestBody Product product) {
        String productName = product.getName();
        return "Product updated successfully: " + productName + " - Price: ";
    }


}