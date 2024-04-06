package com.patika.orderservice.service;

import com.patika.orderservice.exceptions.BusinessException;
import com.patika.orderservice.producer.CreateInvoiceProducer;
import com.patika.orderservice.producer.NotificationProducer;
import com.patika.orderservice.producer.StockUpdateProducer;
import com.patika.orderservice.producer.dto.CreateInvoiceDto;
import com.patika.orderservice.producer.dto.NotificationDto;
import com.patika.orderservice.producer.dto.StockUpdateDto;
import com.patika.orderservice.producer.enums.NotificationType;
import com.patika.orderservice.repository.OrderRepository;
import com.patika.orderservice.client.CustomerServiceClient;
import com.patika.orderservice.client.ProductServiceClient;
import com.patika.orderservice.client.StockServiceClient;
import com.patika.orderservice.dto.OrderDto;
import com.patika.orderservice.dto.OrderItemDto;
import com.patika.orderservice.mapper.DtoConverter;
import com.patika.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final DtoConverter dtoConverter;
    private final NotificationProducer notificationProducer;
    private final StockUpdateProducer stockUpdateProducer;
    private final StockServiceClient stockServiceClient;
    private final ProductServiceClient productServiceClient;
    private final CustomerServiceClient customerServiceClient;
    private final CreateInvoiceProducer createInvoiceProducer;

    @Override
    public OrderDto placeOrder(OrderDto dto) {
        Map<String, Integer> map = dto.getOrderItems().stream()
                .collect(Collectors.toMap(OrderItemDto::getSkuCode, OrderItemDto::getQuantity));

        // stock servisi çağır ve siparişteki tüm item ların stockta olup olmadıgı bilgisni al.
        Boolean inStock = stockServiceClient.stockControl(StockUpdateDto.builder()
                .productStockMap(map)
                .build());

        if (!inStock) {
            throw new BusinessException("Stock Problem");
        }

        Order order = dtoConverter.toOrder(dto);
        Map<UUID, Double> orderTotalPrices = new HashMap<>();
        Map<UUID, Long> orderInfoMap = new HashMap<>();

        order.getOrderItems().forEach(item -> {
            UUID orderNo = order.getOrderNo();
            Double price = item.getQuantity() * productServiceClient.getProductPriceBySku(item.getSkuCode());
            item.setItemPrice(price);
            if (order.getTotalPrice() == null) {
                order.setTotalPrice(price);
            } else {
                order.setTotalPrice(order.getTotalPrice() + price);
            }

            orderTotalPrices.put(orderNo, order.getTotalPrice());
            orderInfoMap.put(orderNo, order.getCustomerId());

        });

        Order savedOrder = orderRepository.saveOrder(order);
        String userMailAddress = customerServiceClient.getUserMailAddressById(order.getCustomerId()).getBody().getData();
        //invoice oluşturmak için event fırlatılacak ( consumer --> invoice service)
        createInvoiceProducer.sendCreateInvoice(CreateInvoiceDto.builder()
                .orderPriceMap(orderTotalPrices)
                .mailAddress(userMailAddress)
                .build());

        stockUpdateProducer.sendStockUpdate(StockUpdateDto.builder()
                .productStockMap(map)
                .build());


        notificationProducer.sendNotification(NotificationDto.builder()
                .to(userMailAddress)
                .notificationType(NotificationType.EMAIL)
                .message(savedOrder.getOrderNo() + " no 'lu siparişiniz alındı!")
                .build());

        return dtoConverter.toOrderDto(savedOrder);
    }
}
