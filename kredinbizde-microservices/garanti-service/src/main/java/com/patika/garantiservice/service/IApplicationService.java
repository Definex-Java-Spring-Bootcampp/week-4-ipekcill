package com.patika.garantiservice.service;

import com.patika.garantiservice.dto.ApplicationRequestDto;
import com.patika.garantiservice.dto.ApplicationResponseDto;

import java.util.List;

public interface IApplicationService {
    ApplicationResponseDto createApplication(ApplicationRequestDto request);

    List<ApplicationResponseDto> getApplicationsByUserId(Long userId);
}
