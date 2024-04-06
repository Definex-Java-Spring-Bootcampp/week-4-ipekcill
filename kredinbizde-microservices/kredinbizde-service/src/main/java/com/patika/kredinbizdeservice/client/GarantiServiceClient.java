package com.patika.kredinbizdeservice.client;

import com.patika.kredinbizdeservice.client.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import com.patika.kredinbizdeservice.factory.BankServiceClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "garanti-service", url = "${spring.cloud.openfeign.client.config.garanti-service.url}")
public interface GarantiServiceClient extends BankServiceClient {

    @PostMapping("api/garanti/v1/applications")
    ApplicationResponse createApplication(@RequestBody ApplicationRequest request);

    @GetMapping("api/garanti/v1/applications/users/{userId}")
    List<ApplicationResponse> getApplicationsByUserId(@PathVariable(value = "userId") final Long userId);
}
