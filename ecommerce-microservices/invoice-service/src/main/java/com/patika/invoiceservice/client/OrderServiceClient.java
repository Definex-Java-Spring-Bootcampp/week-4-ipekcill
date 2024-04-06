package com.patika.invoiceservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "order-service", url = "${spring.cloud.openfeign.client.config.order-service.url}")
public interface OrderServiceClient {

}
