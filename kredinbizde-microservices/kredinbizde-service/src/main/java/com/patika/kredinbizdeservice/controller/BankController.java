package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.controller.model.ApiResponse;
import com.patika.kredinbizdeservice.controller.model.BankDto;
import com.patika.kredinbizdeservice.controller.model.IdRequestDto;
import com.patika.kredinbizdeservice.mapper.ModelMapper;
import com.patika.kredinbizdeservice.service.IBankService;
import com.patika.kredinbizdeservice.model.Bank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/kredinbizde")
@RequiredArgsConstructor
public class BankController {
    private final IBankService bankService;
    private final ModelMapper modelMapper = ModelMapper.INSTANCE;

    @PostMapping("/banks")
    public ResponseEntity<ApiResponse<Bank>> create(@RequestBody BankDto dto) {
        Bank savedBank = bankService.saveBank(dto);
        ApiResponse<Bank> apiResponse = ApiResponse.<Bank>builder()
                .data(savedBank)
                .message("Bank created")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/banks/{id}")
    public ResponseEntity<ApiResponse<Bank>> getBankById(@PathVariable Long id) {
        Bank bank = bankService.getBankById(id);
        ApiResponse<Bank> apiResponse = ApiResponse.<Bank>builder()
                .data(bank)
                .message("Bank found by given id")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/banks")
    public ResponseEntity<ApiResponse<List<Bank>>> getAllBanks() {
        List<Bank> allBanks = bankService.getAllBanks();
        ApiResponse<List<Bank>> apiResponse = ApiResponse.<List<Bank>>builder()
                .data(allBanks)
                .message("Banks found by given id")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/banks/{id}/cards")
    public ResponseEntity<ApiResponse<Bank>> assignCreditCardsToBank(@PathVariable Long id, @RequestBody IdRequestDto request) {
        Bank bank = bankService.getBankById(id);
        Bank updatedBank = bankService.assignCreditCardsToBank(bank, request);
        ApiResponse<Bank> apiResponse = ApiResponse.<Bank>builder()
                .data(updatedBank)
                .message("Credit cards found for bank by given id")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

