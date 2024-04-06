package com.patika.productservice.mapper;

import com.patika.productservice.dto.ProductDto;
import com.patika.productservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {
    //ön yüzde görünmesini istediklerim
    public ProductDto toProductDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .category(product.getCategory())
                .skuCode(product.getSkuCode())
                .price(product.getPrice())
                .build();
    }

    public Product toProduct(ProductDto productDto) {
        return Product.builder()
                .name(productDto.getName())
                .category(productDto.getCategory())
                .price(productDto.getPrice())
                .skuCode(productDto.getSkuCode())
                .build();
    }
}
