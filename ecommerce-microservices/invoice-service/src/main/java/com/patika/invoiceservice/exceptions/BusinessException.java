package com.patika.invoiceservice.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
@Setter
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
