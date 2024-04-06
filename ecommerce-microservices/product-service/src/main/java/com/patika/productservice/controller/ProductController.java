package com.patika.productservice.controller;

import com.patika.productservice.service.ProductService;
import com.patika.productservice.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto request) {
        return productService.createProduct(request);
    }

    @GetMapping("/skuCode")
    public Double getProductPriceBySkuCode(@RequestParam String skuCode) {
        return productService.getProductPriceBySkuCode(skuCode);
    }
}
