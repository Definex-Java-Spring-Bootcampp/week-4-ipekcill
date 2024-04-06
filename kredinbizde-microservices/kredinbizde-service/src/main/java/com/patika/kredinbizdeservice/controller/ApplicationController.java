package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import com.patika.kredinbizdeservice.controller.model.ApiResponse;
import com.patika.kredinbizdeservice.controller.model.ApplicationDto;
import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.service.IApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/kredinbizde")
@RequiredArgsConstructor
public class ApplicationController {
    private final IApplicationService applicationService;

    @Operation(summary = "Create New Application")
    @PostMapping("/applications")
    public ResponseEntity<ApiResponse<Application>> saveApplication(@RequestBody ApplicationDto applicationDto) {
        Application savedApplication = applicationService.saveApplication(applicationDto);
        ApiResponse<Application> apiResponse = ApiResponse.<Application>builder()
                .data(savedApplication)
                .message("Application Created")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Applications")
    @GetMapping("/applications")
    public ResponseEntity<ApiResponse<List<Application>>> getAllApplications(@RequestParam(required = false) LoanType type) {
        List<Application> allApplications = applicationService.getAllApplications(type);
        ApiResponse<List<Application>> apiResponse = ApiResponse.<List<Application>>builder()
                .data(allApplications)
                .message("Applications Found by Given Type")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get Application By Id")
    @GetMapping("/applications/{id}")
    public ResponseEntity<ApiResponse<Application>> getApplicationById(@PathVariable Long id) {
        Application application = applicationService.getApplicationById(id);
        ApiResponse<Application> apiResponse = ApiResponse.<Application>builder()
                .data(application)
                .message("Application Found by Given Id!")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get Applications By User Id")
    @GetMapping("/applications/user/{userId}")
    public ResponseEntity<ApiResponse<List<ApplicationResponse>>> getApplicationByUserId(@PathVariable Long userId) {
        List<ApplicationResponse> applications = applicationService.getApplicationsByUserId(userId);
        ApiResponse<List<ApplicationResponse>> apiResponse = ApiResponse.<List<ApplicationResponse>>builder()
                .data(applications)
                .message("Applications Found by Given userId!")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
