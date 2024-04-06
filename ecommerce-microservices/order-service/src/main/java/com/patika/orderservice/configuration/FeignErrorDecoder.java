package com.patika.orderservice.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.patika.orderservice.controller.model.ApiResponse;
import com.patika.orderservice.exceptions.BusinessException;
import com.patika.orderservice.exceptions.TechnicalException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String message;
        InputStream responseBodyIs = null;
        try {
            responseBodyIs = response.body().asInputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            ApiResponse exceptionMessage = mapper.readValue(responseBodyIs, ApiResponse.class);

            message = exceptionMessage.getMessage();

        } catch (IOException e) {
            e.printStackTrace();
            return new Exception(e.getMessage());
        } finally {
            try {
                if (responseBodyIs != null)
                    responseBodyIs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        switch (response.status()) {
            case 400 -> {
                log.error("Status code " + response.status() + ", methodKey = " + methodKey);
                return new BusinessException(message);
            }
            case 404 -> {
                log.error("Error took place when using Feign client to send HTTP Request. Status code " + response.status() + ", methodKey = " + methodKey);
                return new BusinessException(message);
            }
            default -> {
                log.error("Error took place when using Feign client to send HTTP Request. Status code " + response.status() + ", methodKey = " + methodKey);
                return new TechnicalException(message);
            }
        }
    }
}