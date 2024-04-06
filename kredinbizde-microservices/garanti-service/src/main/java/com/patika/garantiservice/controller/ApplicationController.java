package com.patika.garantiservice.controller;

import com.patika.garantiservice.dto.ApplicationRequestDto;
import com.patika.garantiservice.dto.ApplicationResponseDto;
import com.patika.garantiservice.service.IApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/garanti/v1")
@RequiredArgsConstructor
public class ApplicationController {
    private final IApplicationService applicationService;

    @PostMapping("/applications")
    public ApplicationResponseDto createApplication(@RequestBody ApplicationRequestDto request) {
        return applicationService.createApplication(request);
    }
    @GetMapping("/applications/users/{userId}")
    public List<ApplicationResponseDto> getApplicationsByUserId(@PathVariable Long userId) {
        return applicationService.getApplicationsByUserId(userId);
    }
}
