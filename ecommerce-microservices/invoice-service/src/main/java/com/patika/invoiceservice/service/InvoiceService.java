package com.patika.invoiceservice.service;

import com.patika.invoiceservice.dto.CreateInvoiceDto;
import com.patika.invoiceservice.dto.InvoiceDto;
import com.patika.invoiceservice.exceptions.BusinessException;
import com.patika.invoiceservice.mapper.DtoConverter;
import com.patika.invoiceservice.model.Invoice;
import com.patika.invoiceservice.producer.NotificationProducer;
import com.patika.invoiceservice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService implements IInvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final DtoConverter dtoConverter;
    private final NotificationProducer notificationProducer;

    @Override
    public InvoiceDto saveInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = dtoConverter.toInvoice(invoiceDto);
        return dtoConverter.toInvoiceDto(invoiceRepository.save(invoice));
    }

    @Override
    public InvoiceDto getInvoiceById(Long id) {
        Optional<Invoice> invoiceOpt = invoiceRepository.findById(id);
        Invoice invoice = invoiceOpt.orElseThrow(() -> new BusinessException("Invoice not found by given id"));
        return dtoConverter.toInvoiceDto(invoice);

    }

    @Override
    public InvoiceDto getInvoiceByOrderNo(UUID no) {
        Optional<Invoice> invoiceOpt = invoiceRepository.findByOrderNo(no);
        Invoice invoice = invoiceOpt.orElseThrow(() -> new BusinessException("Invoice not found by given orderNo"));
        return dtoConverter.toInvoiceDto(invoice);
    }

    @Override
    public void createInvoice(CreateInvoiceDto createInvoiceDto) {
        Map<UUID, Double> orderInvoiceMap = createInvoiceDto.getOrderPriceMap();
        for (Map.Entry<UUID, Double> entry : orderInvoiceMap.entrySet()) {

            UUID orderNo = entry.getKey();
            Double totalPrice = entry.getValue();

            Invoice invoice = Invoice.builder()
                    .invoiceDate(LocalDateTime.now())
                    .orderNo(orderNo)
                    .amount(totalPrice)
                    .invoiceNo(UUID.randomUUID())
                    .build();

            Invoice savedInvoice = invoiceRepository.save(invoice);

            notificationProducer.sendNotification();

        }
    }

}
