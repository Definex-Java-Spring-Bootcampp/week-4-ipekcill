package com.patika.kredinbizdeservice.service.impl;

import com.patika.kredinbizdeservice.configuration.CacheNames;
import com.patika.kredinbizdeservice.controller.model.ProductDto;
import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.exceptions.BusinessException;
import com.patika.kredinbizdeservice.model.*;
import com.patika.kredinbizdeservice.repository.ConsumerLoanRepository;
import com.patika.kredinbizdeservice.repository.HouseLoanRepository;
import com.patika.kredinbizdeservice.repository.VehicleLoanRepository;
import com.patika.kredinbizdeservice.service.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.patika.kredinbizdeservice.exceptions.ExceptionMessages.LOAN_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.patika.kredinbizdeservice.exceptions.ExceptionMessages.LOAN_TYPE_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@RequiredArgsConstructor
public class LoanService implements ILoanService {

    private final ConsumerLoanRepository consumerLoanRepository;
    private final VehicleLoanRepository vehicleLoanRepository;
    private final HouseLoanRepository houseLoanRepository;
    private final BankService bankService;

    @Override
    @Cacheable(value = CacheNames.PRODUCTS)
    public List<Loan> getAllLoans() {
        List<Loan> allLoans = new ArrayList<>();
        List<ConsumerLoan> consumerLoans = consumerLoanRepository.findAll();
        allLoans.addAll(consumerLoans);
        List<HouseLoan> houseLoans = houseLoanRepository.findAll();
        allLoans.addAll(houseLoans);
        List<VehicleLoan> vehicleLoans = vehicleLoanRepository.findAll();
        allLoans.addAll(vehicleLoans);

        return allLoans;
    }

    public Loan searchLoanById(Long id) {
        Optional<ConsumerLoan> productOpt = consumerLoanRepository.findById(id);
        if (productOpt.isEmpty()) {
            Optional<HouseLoan> houseProduct = houseLoanRepository.findById(id);
            if (houseProduct.isEmpty()) {
                Optional<VehicleLoan> vehicleProduct = vehicleLoanRepository.findById(id);
                if (vehicleProduct.isEmpty()) {
                    throw new BusinessException(LOAN_NOT_FOUND_EXCEPTION_MESSAGE);
                } else {
                    return vehicleProduct.get();
                }
            } else {
                return houseProduct.get();
            }
        } else {
            return productOpt.get();
        }
    }

    @Override
    @Cacheable(value = CacheNames.PRODUCTS, key = "#id")
    public ConsumerLoan getConsumerLoanById(Long id) {
        Optional<ConsumerLoan> productOpt = consumerLoanRepository.findById(id);
        return productOpt.orElseThrow(() -> new BusinessException(LOAN_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    @Cacheable(value = CacheNames.PRODUCTS, key = "#id")
    public HouseLoan getHouseLoanById(Long id) {
        Optional<HouseLoan> productOpt = houseLoanRepository.findById(id);
        return productOpt.orElseThrow(() -> new BusinessException(LOAN_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    @Cacheable(value = CacheNames.PRODUCTS, key = "#id")
    public VehicleLoan getVehicleLoanById(Long id) {
        Optional<VehicleLoan> productOpt = vehicleLoanRepository.findById(id);
        return productOpt.orElseThrow(() -> new BusinessException(LOAN_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Caching(
            evict = {
                    @CacheEvict(value = CacheNames.PRODUCTS, allEntries = true, condition = "#loan.title != null")
            }
    )
    @Override
    public Loan saveLoan(ProductDto loan) {
        Bank bank = bankService.getBankById(loan.getBank().getId());
        if (loan.getType().equals(LoanType.CONSUMER_LOAN)) {
            ConsumerLoan loan1 = new ConsumerLoan();
            loan1.setAmount(loan.getAmount());
            loan1.setInstallment(loan.getInstallment());
            loan1.setInterestRate(loan.getInterestRate());
            loan1.setTitle(loan.getTitle());
            loan1.setBank(bank);
            return consumerLoanRepository.save(loan1);
        } else if (loan.getType().equals(LoanType.HOUSE_LOAN)) {
            HouseLoan loan1 = new HouseLoan();
            loan1.setAmount(loan.getAmount());
            loan1.setInstallment(loan.getInstallment());
            loan1.setInterestRate(loan.getInterestRate());
            loan1.setTitle(loan.getTitle());
            loan1.setBank(bank);
            return houseLoanRepository.save(loan1);
        } else if (loan.getType().equals(LoanType.VEHICLE_LOAN)) {
            VehicleLoan loan1 = new VehicleLoan();
            loan1.setAmount(loan.getAmount());
            loan1.setInstallment(loan.getInstallment());
            loan1.setInterestRate(loan.getInterestRate());
            loan1.setTitle(loan.getTitle());
            loan1.setVehicleStatusType(loan.getVehicleStatusType());
            loan1.setBank(bank);
            return vehicleLoanRepository.save(loan1);
        } else {
            throw new BusinessException(LOAN_TYPE_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }
}

