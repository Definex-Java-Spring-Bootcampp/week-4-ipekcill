package com.patika.productservice.model;

import com.patika.productservice.enums.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Long id;
    private String skuCode;
    private String name;
    private Category category;
    private Double price;
}
