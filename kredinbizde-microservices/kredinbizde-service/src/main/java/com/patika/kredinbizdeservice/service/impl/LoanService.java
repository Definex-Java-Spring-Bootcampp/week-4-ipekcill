package com.patika.kredinbizdeservice.service.impl;

import com.patika.kredinbizdeservice.configuration.CacheNames;
import com.patika.kredinbizdeservice.controller.model.ProductDto;
import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.exceptions.BusinessException;
import com.patika.kredinbizdeservice.model.*;
import com.patika.kredinbizdeservice.repository.LoanRepository;
import com.patika.kredinbizdeservice.service.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.patika.kredinbizdeservice.exceptions.ExceptionMessages.LOAN_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.patika.kredinbizdeservice.exceptions.ExceptionMessages.LOAN_TYPE_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@RequiredArgsConstructor
public class LoanService implements ILoanService {

    private final LoanRepository loanRepository;
    private final BankService bankService;

    @Override
    @Cacheable(value = CacheNames.PRODUCTS)
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    @Cacheable(value = CacheNames.PRODUCTS, key = "#id")
    public Loan getLoanById(Long id) {
        Optional<Loan> productOpt = loanRepository.findById(id);
        return productOpt.orElseThrow(() -> new BusinessException(LOAN_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Caching(
            evict = {
                    @CacheEvict(value = CacheNames.PRODUCTS, allEntries = true, condition = "#loan.title != null")
            }
    )
    @Override
    public Loan saveLoan(ProductDto loan) {
        if (loan.getType().equals(LoanType.CONSUMER_LOAN)) {
            ConsumerLoan loan1 = new ConsumerLoan();
            loan1.setAmount(loan.getAmount());
            loan1.setInstallment(loan.getInstallment());
            loan1.setInterestRate(loan.getInterestRate());
            loan1.setTitle(loan.getTitle());
            loan1.setBank(bankService.getBankById(loan.getBank().getId()));
            return loanRepository.save(loan1);
        } else if (loan.getType().equals(LoanType.HOUSE_LOAN)) {
            HouseLoan loan1 = new HouseLoan();
            loan1.setAmount(loan.getAmount());
            loan1.setInstallment(loan.getInstallment());
            loan1.setInterestRate(loan.getInterestRate());
            loan1.setTitle(loan.getTitle());
            loan1.setBank(bankService.getBankById(loan.getBank().getId()));
            return loanRepository.save(loan1);
        } else if (loan.getType().equals(LoanType.VEHICLE_LOAN)) {
            VehicleLoan loan1 = new VehicleLoan();
            loan1.setAmount(loan.getAmount());
            loan1.setInstallment(loan.getInstallment());
            loan1.setInterestRate(loan.getInterestRate());
            loan1.setTitle(loan.getTitle());
            loan1.setVehicleStatusType(loan.getVehicleStatusType());
            loan1.setBank(bankService.getBankById(loan.getBank().getId()));
            return loanRepository.save(loan1);
        } else {
            throw new BusinessException(LOAN_TYPE_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }
}

