package com.patika.invoiceservice.listener;

import com.patika.invoiceservice.dto.CreateInvoiceDto;
import com.patika.invoiceservice.service.IInvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class CreateInvoiceListener {
    private final IInvoiceService invoiceService;

    @RabbitListener(queues = "${rabbitmq.consumer.invoice.queue}")
    public void sendNotification(CreateInvoiceDto createInvoiceDto) {
        log.info("kuyruktan okudun: {}", createInvoiceDto);
        invoiceService.createInvoice(createInvoiceDto);
    }
}
