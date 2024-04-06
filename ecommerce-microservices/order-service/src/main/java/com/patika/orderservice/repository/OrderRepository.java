package com.patika.orderservice.repository;

import com.patika.orderservice.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepository {
    private List<Order> orders = new ArrayList<>();

    private final AtomicLong idCounter = new AtomicLong();

    public Order saveOrder(Order order) {
        order.setId(idCounter.getAndIncrement());
        orders.add(order);
        return order;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
