package com.patika.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "product-service", url = "${spring.cloud.openfeign.client.config.product-service.url}")
public interface ProductServiceClient {
    @GetMapping("api/product/skuCode")
    Double getProductPriceBySku(@RequestParam String skuCode);
}
