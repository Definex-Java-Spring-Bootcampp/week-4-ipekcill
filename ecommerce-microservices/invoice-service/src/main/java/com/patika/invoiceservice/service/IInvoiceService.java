package com.patika.invoiceservice.service;

import com.patika.invoiceservice.dto.CreateInvoiceDto;
import com.patika.invoiceservice.dto.InvoiceDto;

import java.util.UUID;

public interface IInvoiceService {

    InvoiceDto saveInvoice(InvoiceDto invoiceDto);

    void createInvoice(CreateInvoiceDto createInvoiceDto);

    InvoiceDto getInvoiceById(Long id);
    InvoiceDto getInvoiceByOrderNo(UUID no);

}
