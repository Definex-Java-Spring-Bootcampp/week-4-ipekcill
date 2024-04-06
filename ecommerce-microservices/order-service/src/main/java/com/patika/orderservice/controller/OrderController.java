package com.patika.orderservice.controller;

import com.patika.orderservice.controller.model.ApiResponse;
import com.patika.orderservice.dto.OrderDto;
import com.patika.orderservice.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderDto>> placeOrder(@RequestBody OrderDto orderDto) {
        OrderDto dto = orderService.placeOrder(orderDto);
        ApiResponse<OrderDto> apiResponse = ApiResponse.<OrderDto>builder()
                .data(dto)
                .message("Order Placed")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }
}
    /*public OrderDto placeOrder(@RequestBody OrderDto orderDto) {
        return orderService.placeOrder(orderDto);

    }*/



