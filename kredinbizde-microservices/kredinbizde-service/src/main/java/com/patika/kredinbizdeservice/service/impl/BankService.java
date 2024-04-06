package com.patika.kredinbizdeservice.service.impl;

import com.patika.kredinbizdeservice.controller.model.BankDto;
import com.patika.kredinbizdeservice.controller.model.IdRequestDto;
import com.patika.kredinbizdeservice.exceptions.BusinessException;
import com.patika.kredinbizdeservice.mapper.ModelMapper;
import com.patika.kredinbizdeservice.model.Bank;
import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.repository.BankRepository;
import com.patika.kredinbizdeservice.repository.CreditCardRepository;
import com.patika.kredinbizdeservice.service.IBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.patika.kredinbizdeservice.exceptions.ExceptionMessages.BANK_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.patika.kredinbizdeservice.exceptions.ExceptionMessages.CREDIT_CARD_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@RequiredArgsConstructor
public class BankService implements IBankService {
    private final BankRepository bankRepository;
    private final CreditCardRepository creditCardRepository;
    private final ModelMapper modelMapper = ModelMapper.INSTANCE;

    @Override
    public Bank saveBank(BankDto dto) {
        return bankRepository.save(modelMapper.toBank(dto));
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public Bank assignCreditCardsToBank(Bank bank, IdRequestDto request) {
        List<CreditCard> creditCards = new ArrayList<>();
        List<Long> ids = request.getIds();
        for (Long cardId : ids) {
            Optional<CreditCard> foundedCreditCard = creditCardRepository.findById(cardId);

            CreditCard creditCard = foundedCreditCard.orElseThrow(() -> new BusinessException(CREDIT_CARD_NOT_FOUND_EXCEPTION_MESSAGE));
            creditCard.setBank(bank);
            creditCards.add(creditCard);
        }
        return bank;
    }

    @Override
    public Bank getBankById(Long id) {
        Optional<Bank> bankOpt = bankRepository.findById(id);
        return bankOpt.orElseThrow(() -> new BusinessException(BANK_NOT_FOUND_EXCEPTION_MESSAGE));
    }
}

