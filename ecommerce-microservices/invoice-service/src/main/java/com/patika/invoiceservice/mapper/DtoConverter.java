package com.patika.invoiceservice.mapper;

import com.patika.invoiceservice.dto.InvoiceDto;
import com.patika.invoiceservice.model.Invoice;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {
    public Invoice toInvoice(InvoiceDto dto) {
        return Invoice.builder()
                .amount(dto.getAmount())
                .invoiceDate(dto.getInvoiceDate())
                .orderNo(dto.getOrderNo())
                .build();
    }

    public InvoiceDto toInvoiceDto(Invoice invoice) {
        return InvoiceDto.builder()
                .amount(invoice.getAmount())
                .invoiceDate(invoice.getInvoiceDate())
                .orderNo(invoice.getOrderNo())
                .build();
    }
}
