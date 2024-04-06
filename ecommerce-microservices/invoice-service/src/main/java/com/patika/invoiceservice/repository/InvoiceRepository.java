package com.patika.invoiceservice.repository;

import com.patika.invoiceservice.model.Invoice;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InvoiceRepository {
    private final AtomicLong idCounter = new AtomicLong();
    List<Invoice> invoices = new ArrayList<>();
    public Invoice save(Invoice invoice) {
        invoice.setId(idCounter.incrementAndGet());
        invoices.add(invoice);
        return invoice;
    }
    public Optional<Invoice> findById(Long id) {
        return invoices.stream()
                .filter(invoice -> invoice.getId().equals(id))
                .findFirst();
    }

    public Optional<Invoice> findByOrderNo(UUID no) {
        return invoices.stream()
                .filter(invoice -> invoice.getOrderNo().equals(no))
                .findFirst();
    }
}
