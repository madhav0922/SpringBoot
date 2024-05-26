package com.example.demo.Product.Model;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private Double price;

    // Taken product in parameter to better be able to use with stream API
    // List<ProductDTO> productDTOs = productRepository
    //                .findAll()
    //                .stream()
    //                .map(ProductDTO::new) -> HERE EXACTLY... to map it directly.
    //                .toList();
    public ProductDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
}
