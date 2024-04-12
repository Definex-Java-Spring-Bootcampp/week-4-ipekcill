package com.patika.kredinbizdeservice.test;

import com.patika.kredinbizdeservice.controller.model.BankDto;
import com.patika.kredinbizdeservice.controller.model.CampaignDto;
import com.patika.kredinbizdeservice.controller.model.CreditCardDto;
import com.patika.kredinbizdeservice.controller.model.IdRequestDto;
import com.patika.kredinbizdeservice.enums.SectorType;
import com.patika.kredinbizdeservice.exceptions.BusinessException;
import com.patika.kredinbizdeservice.model.Bank;
import com.patika.kredinbizdeservice.model.Campaign;
import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.repository.CampaignRepository;
import com.patika.kredinbizdeservice.repository.CreditCardRepository;
import com.patika.kredinbizdeservice.service.impl.BankService;
import com.patika.kredinbizdeservice.service.impl.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreditCardServiceTest {
    @InjectMocks
    private CreditCardService creditCardService;
    @Mock
    private CreditCardRepository creditCardRepository;
    @Mock
    private CampaignRepository campaignRepository;
    @Mock
    private BankService bankService;
    private CreditCard creditCard;
    private CreditCardDto creditCardDto;
    private Bank bank;
    private BankDto bankDto;
    private Campaign campaign;
    private CampaignDto campaignDto;
    private IdRequestDto idRequestDto;
    List<Long> requestIds = List.of(1L);

    @BeforeEach
    void setUp() {
        bank = new Bank();
        bank.setId(1L);
        bank.setName("Test Bank");

        bankDto = BankDto.builder()
                .id(1L)
                .name("Test")
                .build();

        campaign = new Campaign();
        campaign.setId(1L);
        campaign.setContent("Test content");
        campaign.setStartingDate(LocalDate.now());
        campaign.setDueDate(LocalDate.now());
        campaign.setTitle("Test");
        campaign.setSectorType(SectorType.OTHERS);


        creditCard = new CreditCard();
        creditCard.setId(1L);
        creditCard.setFee(BigDecimal.valueOf(0));
        creditCard.setLimit(BigDecimal.valueOf(0));
        creditCard.setBank(bank);
        creditCard.setCampaigns(List.of(campaign));

        creditCardDto = CreditCardDto.builder()
                .id(1L)
                .name("Test")
                .limit(BigDecimal.valueOf(0))
                .fee(BigDecimal.valueOf(0))
                .bank(bankDto)
                .build();

        campaignDto = CampaignDto.builder()
                .id(1L)
                .content("Test content")
                .startingDate(LocalDate.now())
                .dueDate(LocalDate.now())
                .title("Test")
                .sectorType(SectorType.OTHERS)
                .creditCard(creditCardDto)
                .build();

        idRequestDto = IdRequestDto.builder()
                .ids(requestIds)
                .build();
    }

    @Test
    public void should_create_creditCard_successfully() {
        when(bankService.getBankById(bank.getId())).thenReturn(bank);
        when(creditCardRepository.save(any(CreditCard.class))).thenReturn(creditCard);
        CreditCard result = creditCardService.save(creditCardDto);
        assertNotNull(result);
        assertEquals(creditCard, result);
        assertEquals(bank, result.getBank());
        verify(bankService, times(1)).getBankById(1L);
        verify(creditCardRepository, times(1)).save(any(CreditCard.class));
    }

    @Test
    public void should_get_all_creditCards() {
        List<CreditCard> creditCardList = Collections.singletonList(creditCard);
        when(creditCardRepository.findAll()).thenReturn(creditCardList);
        List<CreditCard> result = creditCardService.getAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(creditCard.getId(), result.get(0).getId());
        verify(creditCardRepository, times(1)).findAll();
    }

    @Test
    public void should_get_creditCard_by_id() {
        when(creditCardRepository.findById(anyLong())).thenReturn(Optional.of(creditCard));
        CreditCard result = creditCardService.getById(1L);
        assertEquals(creditCard.getId(), result.getId());
        verify(creditCardRepository, times(1)).findById(anyLong());
    }

    @Test
    public void should_assign_campaigns_to_creditCard_Success() {
        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));

        CreditCard result = creditCardService.assignCampaignsToCreditCard(creditCard, idRequestDto);

        assertEquals(creditCard, result);
        assertEquals(1, result.getCampaigns().size());
        assertTrue(result.getCampaigns().contains(campaign));
        assertFalse(result.getCampaigns().contains(null));

        verify(campaignRepository, times(1)).findById(anyLong());
        verify(creditCardRepository, never()).save(any(CreditCard.class));
    }

    @Test
    public void should_throw_exception_creditCard_not_found() {
        when(campaignRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> creditCardService.assignCampaignsToCreditCard(creditCard, idRequestDto));
        verify(campaignRepository, times(1)).findById(anyLong());
        verify(creditCardRepository, never()).save(any(CreditCard.class));
    }
}
