package com.patika.kredinbizdeservice.controller.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class IdRequestDto {
    private List<Long> ids;
}
