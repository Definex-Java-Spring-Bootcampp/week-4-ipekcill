package com.patika.kredinbizdeservice.factory;

import com.patika.kredinbizdeservice.client.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BankServiceClient {
    ApplicationResponse createApplication(@RequestBody ApplicationRequest request);

    List<ApplicationResponse> getApplicationsByUserId(@PathVariable(value = "userId") final Long userId);
}
