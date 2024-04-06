package com.patika.kredinbizdeservice.service.impl;

import com.patika.kredinbizdeservice.controller.model.CampaignDto;
import com.patika.kredinbizdeservice.exceptions.BusinessException;
import com.patika.kredinbizdeservice.mapper.ModelMapper;
import com.patika.kredinbizdeservice.model.Campaign;
import com.patika.kredinbizdeservice.repository.CampaignRepository;
import com.patika.kredinbizdeservice.service.ICampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.patika.kredinbizdeservice.exceptions.ExceptionMessages.CAMPAIGN_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@RequiredArgsConstructor
public class CampaignService implements ICampaignService {
    private final CampaignRepository campaignRepository;
    private final ModelMapper modelMapper = ModelMapper.INSTANCE;
    @Override
    public Campaign save(CampaignDto campaignDto) {
        Campaign campaign = modelMapper.toCampaign(campaignDto);
        return campaignRepository.save(campaign);
    }
    @Override
    public List<Campaign> getAll() {
        return campaignRepository.findAll();
    }

    @Override
    public List<Campaign> getAllByDate() {
        return campaignRepository.findAllOrderByStartDateDesc();
    }

    @Override
    public Campaign getById(Long id) {
        Optional<Campaign> campaignOpt = campaignRepository.findById(id);
        return campaignOpt.orElseThrow(() -> new BusinessException(CAMPAIGN_NOT_FOUND_EXCEPTION_MESSAGE));
    }
}
