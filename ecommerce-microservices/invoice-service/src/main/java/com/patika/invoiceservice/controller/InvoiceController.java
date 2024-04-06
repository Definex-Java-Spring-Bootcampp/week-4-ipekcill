package com.patika.invoiceservice.controller;

import com.patika.invoiceservice.dto.InvoiceDto;
import com.patika.invoiceservice.service.IInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("api/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final IInvoiceService invoiceService;
    @PostMapping
    public InvoiceDto createInvoice(@RequestBody InvoiceDto request) {
        return invoiceService.saveInvoice(request);
    }

    @GetMapping("/{id}")
    public InvoiceDto getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }
}
