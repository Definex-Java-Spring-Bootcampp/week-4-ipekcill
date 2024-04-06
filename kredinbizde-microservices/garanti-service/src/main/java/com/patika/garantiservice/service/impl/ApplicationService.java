package com.patika.garantiservice.service.impl;

import com.patika.garantiservice.data.entity.Application;
import com.patika.garantiservice.dto.ApplicationRequestDto;
import com.patika.garantiservice.dto.ApplicationResponseDto;
import com.patika.garantiservice.mapper.ApplicationMapper;
import com.patika.garantiservice.data.repository.ApplicationRepository;
import com.patika.garantiservice.exceptions.BusinessException;
import com.patika.garantiservice.exceptions.ExceptionMessages;
import com.patika.garantiservice.service.IApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService implements IApplicationService {
    private final ApplicationRepository applicationRepository;

    private final ApplicationMapper applicationMapper;


    public ApplicationResponseDto createApplication(ApplicationRequestDto request) {
        log.info("Başvuru iletilmeye başlıyor.. " + request.getUserId() + " " + request.getProductId());
        Application application = applicationMapper.toApplication(request);

        if (applicationRepository.getApplicationByUserIdAndProductId(application.getUserId(), application.getProductId()).isPresent()) {
            throw new BusinessException(ExceptionMessages.DUPLICATE_APPLICATION);
        }

        Application savedApplication = applicationRepository.save(application);
        log.info("Başvuru tamamlandı" + savedApplication.getApplicationStatus().name());
        return applicationMapper.toResponse(savedApplication);
    }

    public List<ApplicationResponseDto> getApplicationsByUserId(Long userId) {
        return applicationMapper.toResponseList(applicationRepository.getAllApplicationsByUserId(userId));
    }
}
