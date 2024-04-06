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

@FeignClient(value = "akbank-service", url = "${spring.cloud.openfeign.client.config.akbank-service.url}")
public interface AkbankServiceClient extends BankServiceClient {

    @PostMapping("api/akbank/v1/applications")
    ApplicationResponse createApplication(@RequestBody ApplicationRequest request);

    @GetMapping("/applications/users/{userId}")
    List<ApplicationResponse> getApplicationsByUserId(@PathVariable(value = "userId") final Long userId);
}
