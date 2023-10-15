
package com.example.urbancart.config;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Product product1 = new Product(
                "Product 1",
                "This is product 1",
                100,
                10
            );
            Product product2 = new Product(
                "Product 2",
                "This is product 2",
                200,
                20
            );
            productRepository.saveAll(
                List.of(product1, product2)
            );
        };
    }

}