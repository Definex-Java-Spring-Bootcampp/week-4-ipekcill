package com.patika.orderservice.client;

import com.patika.orderservice.configuration.FeignErrorDecoder;
import com.patika.orderservice.controller.model.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "customer-service", url = "${spring.cloud.openfeign.client.config.customer-service.url}", configuration = FeignErrorDecoder.class)
public interface CustomerServiceClient {
    @GetMapping("api/customer/{id}")
    ResponseEntity<ApiResponse<String>> getUserMailAddressById(@PathVariable Long id);

}
