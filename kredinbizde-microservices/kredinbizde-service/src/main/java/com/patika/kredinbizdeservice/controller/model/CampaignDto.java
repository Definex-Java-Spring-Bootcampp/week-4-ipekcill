package com.patika.kredinbizdeservice.controller.model;

import com.patika.kredinbizdeservice.enums.SectorType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CampaignDto {
    private Long id;
    private String title;
    private String content;
    private LocalDate dueDate;
    private LocalDate startingDate;
    private SectorType sectorType;
}
