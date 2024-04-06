package com.patika.productservice.service;

import com.patika.productservice.dto.ProductDto;

public interface IProductService {
    ProductDto createProduct(ProductDto request);
    Double getProductPriceBySkuCode(String skuCode);
}
