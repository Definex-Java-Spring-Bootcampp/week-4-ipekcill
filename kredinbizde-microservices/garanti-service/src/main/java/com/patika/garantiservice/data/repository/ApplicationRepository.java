package com.patika.garantiservice.data.repository;

import com.patika.garantiservice.data.entity.Application;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ApplicationRepository {

    private List<Application> applicationList = new ArrayList<>();

    private final AtomicLong idCounter = new AtomicLong();

    public Application save(Application application) {
        application.setId(idCounter.incrementAndGet());
        applicationList.add(application);
        return application;
    }

    public List<Application> getAllApplicationsByUserId(Long userId) {
        return applicationList.stream().filter(f -> f.getUserId().equals(userId)).collect(Collectors.toList());
    }

    public Optional<Application> getApplicationByUserIdAndProductId(Long userId, Long productId) {
        return applicationList.stream().filter(f -> f.getUserId().equals(userId) && f.getProductId().equals(productId)).findFirst();
    }

    public List<Application> getAll() {
        return applicationList;
    }
}
