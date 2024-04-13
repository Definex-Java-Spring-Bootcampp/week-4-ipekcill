package com.patika.kredinbizdeservice.test;

import com.patika.kredinbizdeservice.controller.model.BankDto;
import com.patika.kredinbizdeservice.controller.model.CreditCardDto;
import com.patika.kredinbizdeservice.controller.model.IdRequestDto;
import com.patika.kredinbizdeservice.controller.model.UserDto;
import com.patika.kredinbizdeservice.exceptions.BusinessException;
import com.patika.kredinbizdeservice.model.Bank;
import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.repository.BankRepository;
import com.patika.kredinbizdeservice.repository.CreditCardRepository;
import com.patika.kredinbizdeservice.repository.UserRepository;
import com.patika.kredinbizdeservice.service.impl.BankService;
import com.patika.kredinbizdeservice.service.impl.UserService;
import com.patika.kredinbizdeservice.exceptions.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {
    @InjectMocks
    private BankService bankService;
    @Mock
    private BankRepository bankRepository;
    @Mock
    private CreditCardRepository creditCardRepository;
    private Bank bank;
    private BankDto bankDto;
    private CreditCard creditCard;
    private IdRequestDto idRequestDto;
    private IdRequestDto idRequestDto2;
    List<Long> requestIds = List.of(1L);

    @BeforeEach
    void setUp() {
        bankDto = BankDto.builder()
                .name("Test bank")
                .id(1L)
                .build();

        bank = new Bank();
        bank.setId(1L);
        bank.setName("Test Bank");

        creditCard = new CreditCard();
        creditCard.setId(1L);
        creditCard.setFee(BigDecimal.valueOf(0));
        creditCard.setLimit(BigDecimal.valueOf(0));
        creditCard.setBank(bank);

        idRequestDto = IdRequestDto.builder()
                .ids(requestIds)
                .build();
    }

    @Test
    public void should_create_bank_successfully() {
        when(bankRepository.save(any(Bank.class))).thenReturn(bank);
        bankService.saveBank(bankDto);
        assertEquals(bank.getId(), 1L);
        verify(bankRepository, times(1)).save(any(Bank.class));

    }
    @Test
    public void should_get_all_banks() {
        List<Bank> bankList = Collections.singletonList(bank);
        when(bankRepository.findAll()).thenReturn(bankList);
        List<Bank> result = bankService.getAllBanks();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(bank.getId(), result.get(0).getId());
        verify(bankRepository, times(1)).findAll();
    }
    @Test
    public void should_get_bank_by_id() {
        when(bankRepository.findById(anyLong())).thenReturn(Optional.of(bank));
        Bank result = bankService.getBankById(1L);
        assertEquals(bank.getId(), result.getId());
        verify(bankRepository, times(1)).findById(anyLong());
    }

    @Test
    public void should_throw_exception_when_getting_bank_by_invalid_id() {
        when(bankRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> bankService.getBankById(999L));
        verify(bankRepository, times(1)).findById(anyLong());
    }
    @Test
    public void testAssignCreditCardsToBank_Success() {
        when(creditCardRepository.findById(1L)).thenReturn(Optional.of(creditCard));

        Bank result = bankService.assignCreditCardsToBank(bank, idRequestDto);

        assertEquals(bank, result);
        assertEquals(bank, creditCard.getBank());

        verify(creditCardRepository, times(1)).findById(anyLong());
        verify(bankRepository, never()).save(any(Bank.class));
    }
    @Test
    public void testAssignCreditCardsToBank_CreditCardNotFound() {
        when(creditCardRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> bankService.assignCreditCardsToBank(bank, idRequestDto));
        verify(creditCardRepository, times(1)).findById(anyLong());
        verify(bankRepository, never()).save(any(Bank.class));
    }
}
