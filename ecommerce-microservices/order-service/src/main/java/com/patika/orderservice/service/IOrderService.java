package com.patika.orderservice.service;

import com.patika.orderservice.dto.OrderDto;

public interface IOrderService {
    OrderDto placeOrder(OrderDto dto);
}
