package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.controller.model.CampaignDto;
import com.patika.kredinbizdeservice.model.Campaign;

import java.util.List;

public interface ICampaignService {
    Campaign save(CampaignDto campaignDto);

    List<Campaign> getAll();

    List<Campaign> getAllByDate();

    Campaign getById(Long id);
}
