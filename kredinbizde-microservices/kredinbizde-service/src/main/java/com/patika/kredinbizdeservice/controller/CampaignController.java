package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.controller.model.ApiResponse;
import com.patika.kredinbizdeservice.controller.model.CampaignDto;
import com.patika.kredinbizdeservice.service.ICampaignService;
import com.patika.kredinbizdeservice.model.Campaign;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/kredinbizde")
@RequiredArgsConstructor
public class CampaignController {
    private final ICampaignService campaignService;

    @PostMapping("/campaigns")
    public ResponseEntity<ApiResponse<Campaign>> create(@RequestBody CampaignDto dto) {
        Campaign savedCampaign = campaignService.save(dto);
        ApiResponse<Campaign> apiResponse = ApiResponse.<Campaign>builder()
                .data(savedCampaign)
                .message("Campaign created")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/campaigns")
    public ResponseEntity<ApiResponse<List<Campaign>>> getAll() {
        List<Campaign> allCampaigns = campaignService.getAll();
        ApiResponse<List<Campaign>> apiResponse = ApiResponse.<List<Campaign>>builder()
                .data(allCampaigns)
                .message("Campaigns found")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);


    }

    @GetMapping("/campaigns/{id}")
    public ResponseEntity<ApiResponse<Campaign>> getById(@PathVariable Long id) {
        Campaign campaign = campaignService.getById(id);
        ApiResponse<Campaign> apiResponse = ApiResponse.<Campaign>builder()
                .data(campaign)
                .message("Campaign found by given id")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @GetMapping("/campaigns/latest")
    public ResponseEntity<ApiResponse<List<Campaign>>> getAllCampaignsByDate() {
        List<Campaign> campaigns = campaignService.getAllByDate();
        ApiResponse<List<Campaign>> apiResponse = ApiResponse.<List<Campaign>>builder()
                .data(campaigns)
                .message("Campaigns ordered")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
}
