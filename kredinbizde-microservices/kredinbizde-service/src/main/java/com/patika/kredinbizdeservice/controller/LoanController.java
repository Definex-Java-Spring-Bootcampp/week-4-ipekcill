package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.controller.model.ApiResponse;
import com.patika.kredinbizdeservice.controller.model.ProductDto;
import com.patika.kredinbizdeservice.service.ILoanService;
import com.patika.kredinbizdeservice.model.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/kredinbizde")
@RequiredArgsConstructor
public class LoanController {
    private final ILoanService loanService;

    @PostMapping("/loans")
    public ResponseEntity<ApiResponse<Loan>> createLoan(@RequestBody ProductDto loan) {
        Loan savedLoan = loanService.saveLoan(loan);
        ApiResponse<Loan> apiResponse = ApiResponse.<Loan>builder()
                .data(savedLoan)
                .message("Loan Created")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/loans")
    public ResponseEntity<ApiResponse<List<Loan>>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        ApiResponse<List<Loan>> apiResponse = ApiResponse.<List<Loan>>builder()
                .data(loans)
                .message("Loans found")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/loans/{id}")
    public ResponseEntity<ApiResponse<Loan>> getLoanById(@PathVariable Long id) {
        Loan loan = loanService.searchLoanById(id);
        ApiResponse<Loan> apiResponse = ApiResponse.<Loan>builder()
                .data(loan)
                .message("Loan found by given id")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

