package com.patika.orderservice.mapper;

import com.patika.orderservice.dto.OrderDto;
import com.patika.orderservice.dto.OrderItemDto;
import com.patika.orderservice.model.Order;
import com.patika.orderservice.model.OrderItem;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DtoConverter {
    public Order toOrder(OrderDto dto) {
        return Order.builder()
                .createdDate(LocalDateTime.now())
                .customerId(dto.getCustomerId())
                .orderItems(dto.getOrderItems().stream().map(this::toOrderItem).collect(Collectors.toList()))
                .orderNo(UUID.randomUUID())
                .build();
    }

    public OrderItem toOrderItem(OrderItemDto dto) {
        return OrderItem.builder()
                .skuCode(dto.getSkuCode())
                .quantity(dto.getQuantity())
                .build();
    }


    public OrderDto toOrderDto(Order order) {
        return OrderDto.builder()
                .createdDate(order.getCreatedDate())
                .totalPrice(order.getTotalPrice())
                .orderItems(order.getOrderItems().stream().map(this::toOrderItemDto).collect(Collectors.toList()))
                .orderNo(order.getOrderNo())
                .customerId(order.getCustomerId())
                .build();
    }

    public OrderItemDto toOrderItemDto(OrderItem orderItem) {
        return OrderItemDto.builder()
                .skuCode(orderItem.getSkuCode())
                .quantity(orderItem.getQuantity())
                .itemPrice(orderItem.getItemPrice())
                .build();
    }
}
