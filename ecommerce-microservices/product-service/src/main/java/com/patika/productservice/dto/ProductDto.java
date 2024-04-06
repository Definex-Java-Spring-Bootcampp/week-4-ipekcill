package com.patika.productservice.dto;

import com.patika.productservice.enums.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private Long id;
    private String skuCode;
    private String name;
    private Category category;
    private Double price;
}
