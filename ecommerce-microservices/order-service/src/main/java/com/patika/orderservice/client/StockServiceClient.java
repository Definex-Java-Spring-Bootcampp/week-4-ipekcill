package com.patika.orderservice.client;

import com.patika.orderservice.producer.dto.StockUpdateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "stock-service", url = "${spring.cloud.openfeign.client.config.stock-service.url}")
public interface StockServiceClient {

    @PostMapping("api/stock/control")
    Boolean stockControl(@RequestBody StockUpdateDto request);
}
