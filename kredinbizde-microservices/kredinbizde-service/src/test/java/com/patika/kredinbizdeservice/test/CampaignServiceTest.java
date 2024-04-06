package com.patika.kredinbizdeservice.test;

import com.patika.kredinbizdeservice.controller.model.CampaignDto;
import com.patika.kredinbizdeservice.enums.SectorType;
import com.patika.kredinbizdeservice.model.Campaign;
import com.patika.kredinbizdeservice.repository.CampaignRepository;
import com.patika.kredinbizdeservice.service.impl.CampaignService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampaignServiceTest {
    @InjectMocks
    private CampaignService campaignService;
    @Mock
    private CampaignRepository campaignRepository;
    private CampaignDto campaignDto;
    private Campaign campaign1;
    private Campaign campaign2;
    private Campaign campaign3;

    @BeforeEach
    void setUp() {
        campaignDto = CampaignDto.builder()
                .id(1L)
                .content("Test content")
                .startingDate(LocalDate.now())
                .dueDate(LocalDate.now())
                .title("Test")
                .sectorType(SectorType.OTHERS)
                .build();

        campaign1 = new Campaign();
        campaign1.setId(1L);
        campaign1.setContent("Test content");
        campaign1.setStartingDate(LocalDate.now());
        campaign1.setDueDate(LocalDate.now());
        campaign1.setTitle("Test");
        campaign1.setSectorType(SectorType.OTHERS);

        campaign2 = new Campaign();
        campaign2.setId(1L);
        campaign2.setContent("Test content");
        campaign2.setStartingDate(LocalDate.now());
        campaign2.setDueDate(LocalDate.now());
        campaign2.setTitle("Test");
        campaign2.setSectorType(SectorType.OTHERS);

        campaign3 = new Campaign();
        campaign3.setId(1L);
        campaign3.setContent("Test content");
        campaign3.setStartingDate(LocalDate.of(2024, 4, 2));
        campaign3.setDueDate(LocalDate.now());
        campaign3.setTitle("Test");
        campaign3.setSectorType(SectorType.OTHERS);

    }

    @Test
    public void should_create_campaign_successfully() {
        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign1);
        campaignService.save(campaignDto);
        assertEquals(campaign1.getId(), 1L);
        verify(campaignRepository, times(1)).save(any(Campaign.class));
    }

    @Test
    public void should_get_all_campaigns() {
        List<Campaign> campaignList = Collections.singletonList(campaign1);
        when(campaignRepository.findAll()).thenReturn(campaignList);
        List<Campaign> result = campaignService.getAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(campaign1.getId(), result.get(0).getId());
        verify(campaignRepository, times(1)).findAll();
    }

    @Test
    public void should_get_campaign_by_id() {
        when(campaignRepository.findById(anyLong())).thenReturn(Optional.of(campaign1));
        Campaign result = campaignService.getById(1L);
        assertEquals(campaign1.getId(), result.getId());
        verify(campaignRepository, times(1)).findById(anyLong());
    }

    @Test
    public void should_get_campaigns_order_by_date() {
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(campaign1);
        campaigns.add(campaign3);
        campaigns.add(campaign2);
        when(campaignRepository.findAllOrderByStartDateDesc()).thenReturn(campaigns);
        List<Campaign> sortedCampaigns = campaignService.getAllByDate();
        assertEquals(campaigns, sortedCampaigns);
    }
}
