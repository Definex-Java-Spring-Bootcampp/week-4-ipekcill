package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.controller.model.ProductDto;
import com.patika.kredinbizdeservice.model.ConsumerLoan;
import com.patika.kredinbizdeservice.model.HouseLoan;
import com.patika.kredinbizdeservice.model.Loan;
import com.patika.kredinbizdeservice.model.VehicleLoan;

import java.util.List;

public interface ILoanService {
    List<Loan> getAllLoans();

    Loan searchLoanById(Long id);

    ConsumerLoan getConsumerLoanById(Long id);

    HouseLoan getHouseLoanById(Long id);

    VehicleLoan getVehicleLoanById(Long id);

    Loan saveLoan(ProductDto loan);
}
