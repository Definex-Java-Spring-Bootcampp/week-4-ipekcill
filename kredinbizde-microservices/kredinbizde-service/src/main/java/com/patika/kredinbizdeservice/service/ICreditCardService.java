package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.controller.model.CreditCardDto;
import com.patika.kredinbizdeservice.controller.model.IdRequestDto;
import com.patika.kredinbizdeservice.model.CreditCard;

import java.util.List;

public interface ICreditCardService {
    CreditCard save(CreditCardDto dto);

    List<CreditCard> getAll();

    CreditCard getById(Long id);

    CreditCard assignCampaignsToCreditCard(CreditCard creditCard, IdRequestDto request);
}
