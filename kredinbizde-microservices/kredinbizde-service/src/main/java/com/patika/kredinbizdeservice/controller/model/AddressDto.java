package com.patika.kredinbizdeservice.controller.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private Long id;
    private String addressTitle;
    private String addressDescription;
    private String province;
}
