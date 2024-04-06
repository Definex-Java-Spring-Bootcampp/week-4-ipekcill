package com.patika.kredinbizdeservice.factory;

import com.patika.kredinbizdeservice.client.AkbankServiceClient;
import com.patika.kredinbizdeservice.client.GarantiServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankServiceClientFactory {

    private final GarantiServiceClient garantiServiceClient;
    private final AkbankServiceClient akbankServiceClient;
    public BankServiceClient getBankServiceClient(String bankName) {
        return switch (bankName) {
            case "Garanti" -> garantiServiceClient;
            case "Akbank" -> akbankServiceClient;
            default -> throw new IllegalArgumentException("Geçersiz banka ismi: " + bankName);
        };
    }
}
