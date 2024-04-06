package com.patika.productservice.service;

import com.patika.productservice.exceptions.BusinessException;
import com.patika.productservice.repository.ProductRepository;
import com.patika.productservice.dto.ProductDto;
import com.patika.productservice.mapper.DtoConverter;
import com.patika.productservice.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final DtoConverter dtoConverter;
    private final ProductRepository productRepository;
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = dtoConverter.toProduct(productDto);
        return dtoConverter.toProductDto(productRepository.save(product));
    }

    @Override
    public Double getProductPriceBySkuCode(String skuCode) {
        Optional<Product> product = productRepository.findBySkuCode(skuCode);

        return product.map(Product::getPrice).orElseThrow(() -> new BusinessException("Product not found by given skuCode"));
    }
}
