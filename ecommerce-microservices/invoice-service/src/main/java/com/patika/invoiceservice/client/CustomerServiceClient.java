package com.patika.invoiceservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "customer-service", url = "${spring.cloud.openfeign.client.config.customer-service.url}")
public interface CustomerServiceClient {
    @GetMapping("api/customer/{id}")
    ResponseEntity<String> getUserMailAddressById(@PathVariable Long id);
}
