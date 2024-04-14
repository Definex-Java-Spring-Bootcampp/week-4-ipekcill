package com.patika.garantiservice.mapper;

import com.patika.garantiservice.data.entity.Application;
import com.patika.garantiservice.dto.ApplicationRequestDto;
import com.patika.garantiservice.dto.ApplicationResponseDto;
import com.patika.garantiservice.enums.ApplicationStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApplicationMapper {
    public Application toApplication(ApplicationRequestDto request) {
        return Application.builder()
                .userId(request.getUserId())
                .productId(request.getProductId())
                .createDate(LocalDateTime.now())
                .applicationStatus(ApplicationStatus.INITIAL)
                .productLoanType(request.getProductLoanType())
                .build();
    }

    public ApplicationResponseDto toResponse(Application application) {
        return ApplicationResponseDto.builder()
                .userId(application.getUserId())
                .createDate(application.getCreateDate())
                .applicationStatus(application.getApplicationStatus())
                .productId(application.getProductId())
                .build();
    }
    public List<ApplicationResponseDto> toResponseList(List<Application> applications) {
        return applications.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
