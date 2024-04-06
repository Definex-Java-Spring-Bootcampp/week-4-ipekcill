package com.patika.productservice.repository;

import com.patika.productservice.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    List<Product> products = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();
    public Product save(Product product) {
        product.setId(idCounter.getAndIncrement());
        products.add(product);
        return product;
    }

    public Optional<Product> findBySkuCode(String skuCode) {
        return products.stream()
                .filter(product -> product.getSkuCode().equals(skuCode))
                .findFirst();
    }
}
