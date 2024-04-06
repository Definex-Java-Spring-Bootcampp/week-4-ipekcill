package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.controller.model.ApiResponse;
import com.patika.kredinbizdeservice.controller.model.CreditCardDto;
import com.patika.kredinbizdeservice.controller.model.IdRequestDto;
import com.patika.kredinbizdeservice.service.ICreditCardService;
import com.patika.kredinbizdeservice.model.CreditCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/kredinbizde")
@RequiredArgsConstructor
public class CreditCardController {
    private final ICreditCardService creditCardService;

    @PostMapping("/cards")
    public ResponseEntity<ApiResponse<CreditCard>> create(@RequestBody CreditCardDto dto) {
        CreditCard savedCreditCard = creditCardService.save(dto);
        ApiResponse<CreditCard> apiResponse = ApiResponse.<CreditCard>builder()
                .data(savedCreditCard)
                .message("CreditCard Created")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }


    @GetMapping("/cards")
    public ResponseEntity<ApiResponse<List<CreditCard>>> getAll() {
        List<CreditCard> allCreditCards = creditCardService.getAll();
        ApiResponse<List<CreditCard>> apiResponse = ApiResponse.<List<CreditCard>>builder()
                .data(allCreditCards)
                .message("CreditCards found")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/cards/{id}")
    public ResponseEntity<ApiResponse<CreditCard>> getById(@PathVariable Long id) {
        CreditCard creditCard = creditCardService.getById(id);

        ApiResponse<CreditCard> apiResponse = ApiResponse.<CreditCard>builder()
                .data(creditCard)
                .message("CreditCard found by given id")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @PutMapping("/cards/{id}/campaigns")
    public ResponseEntity<ApiResponse<CreditCard>> assignCampaignsToCreditCard(@PathVariable Long id, @RequestBody IdRequestDto campaignIds) {
        CreditCard creditCard = creditCardService.getById(id);
        CreditCard updatedCreditCard = creditCardService.assignCampaignsToCreditCard(creditCard, campaignIds);
        ApiResponse<CreditCard> apiResponse = ApiResponse.<CreditCard>builder()
                .data(updatedCreditCard)
                .message("Campaigns assigned credit card by given id")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
