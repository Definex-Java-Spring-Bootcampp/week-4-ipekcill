package com.patika.kredinbizdeservice.service.impl;

import com.patika.kredinbizdeservice.configuration.CacheNames;
import com.patika.kredinbizdeservice.controller.model.CreditCardDto;
import com.patika.kredinbizdeservice.controller.model.IdRequestDto;
import com.patika.kredinbizdeservice.exceptions.BusinessException;
import com.patika.kredinbizdeservice.mapper.ModelMapper;
import com.patika.kredinbizdeservice.model.Bank;
import com.patika.kredinbizdeservice.model.Campaign;
import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.repository.CampaignRepository;
import com.patika.kredinbizdeservice.repository.CreditCardRepository;
import com.patika.kredinbizdeservice.service.ICreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.patika.kredinbizdeservice.exceptions.ExceptionMessages.CAMPAIGN_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.patika.kredinbizdeservice.exceptions.ExceptionMessages.CREDIT_CARD_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@RequiredArgsConstructor
public class CreditCardService implements ICreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final CampaignRepository campaignRepository;
    private final BankService bankService;

    private final ModelMapper modelMapper = ModelMapper.INSTANCE;

    @Caching(
            evict = {
                    @CacheEvict(value = CacheNames.PRODUCTS, allEntries = true, condition = "#dto.name != null")
            }
    )
    @Override
    public CreditCard save(CreditCardDto dto) {
        Bank bank = bankService.getBankById(dto.getBank().getId());
        CreditCard creditCard = modelMapper.toCreditCard(dto);
        creditCard.setBank(bank);
        return creditCardRepository.save(creditCard);
    }

    @Override
    @Cacheable(value = CacheNames.PRODUCTS)
    public List<CreditCard> getAll() {
        return creditCardRepository.findAll();
    }

    @Override
    @Cacheable(value = CacheNames.PRODUCTS, key = "#id")
    public CreditCard getById(Long id) {
        Optional<CreditCard> creditCardOpt = creditCardRepository.findById(id);

        return creditCardOpt.orElseThrow(() -> new BusinessException(CREDIT_CARD_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public CreditCard assignCampaignsToCreditCard(CreditCard creditCard, IdRequestDto request) {
        List<Campaign> assignedCampaigns = new ArrayList<>();
        List<Long> ids = request.getIds();
        for (Long campaignId : ids) {
            Optional<Campaign> campaignOpt = campaignRepository.findById(campaignId);
            campaignOpt.ifPresentOrElse(
                    assignedCampaigns::add,
                    () -> {
                        throw new BusinessException(CAMPAIGN_NOT_FOUND_EXCEPTION_MESSAGE);
                    }
            );
        }
        creditCard.setCampaigns(assignedCampaigns);
        return creditCard;
    }
}



