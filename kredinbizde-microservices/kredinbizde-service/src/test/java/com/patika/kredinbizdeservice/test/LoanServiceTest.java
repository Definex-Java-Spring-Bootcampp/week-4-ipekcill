package com.patika.kredinbizdeservice.test;

import com.patika.kredinbizdeservice.controller.model.ProductDto;
import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.model.*;
import com.patika.kredinbizdeservice.repository.LoanRepository;
import com.patika.kredinbizdeservice.service.impl.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {
    @InjectMocks
    private LoanService loanService;
    @Mock
    private LoanRepository loanRepository;
    private ProductDto consumerLoanProduct;
    private ProductDto houseLoanProduct;
    private ProductDto vehicleLoanProduct;
    private ConsumerLoan consumerLoan;
    private HouseLoan houseLoan;
    private VehicleLoan vehicleLoan;

    @BeforeEach
    void setUp() {
        Bank bank = new Bank();
        bank.setId(1L);
        bank.setName("Test Bank");

        consumerLoanProduct = ProductDto.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(0))
                .installment(3)
                .interestRate(1.2)
                .title("Test")
                .type(LoanType.CONSUMER_LOAN)
                .build();

        houseLoanProduct = ProductDto.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(0))
                .installment(3)
                .interestRate(1.2)
                .title("Test")
                .type(LoanType.HOUSE_LOAN)
                .build();

        vehicleLoanProduct = ProductDto.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(0))
                .installment(3)
                .interestRate(1.2)
                .title("Test")
                .type(LoanType.VEHICLE_LOAN)
                .build();

        consumerLoan = new ConsumerLoan();
        consumerLoan.setId(1L);
        consumerLoan.setAmount(BigDecimal.valueOf(1000));
        consumerLoan.setInstallment(12);
        consumerLoan.setInterestRate(1.5);
        consumerLoan.setTitle("test");
        consumerLoan.setBank(bank);

        houseLoan = new HouseLoan();
        houseLoan.setId(1L);
        houseLoan.setAmount(BigDecimal.valueOf(1000));
        houseLoan.setInstallment(12);
        houseLoan.setInterestRate(1.5);
        houseLoan.setTitle("test");
        houseLoan.setBank(bank);


        vehicleLoan = new VehicleLoan();
        vehicleLoan.setId(1L);
        vehicleLoan.setAmount(BigDecimal.valueOf(1000));
        vehicleLoan.setInstallment(12);
        vehicleLoan.setInterestRate(1.5);
        vehicleLoan.setTitle("test");
        vehicleLoan.setBank(bank);
    }

    @Test
    public void should_create_consumer_loan_successfully() {
        when(loanRepository.save(any(Loan.class))).thenReturn(consumerLoan);
        Loan result = loanService.saveLoan(consumerLoanProduct);

        assertNotNull(result);
        assertEquals(consumerLoan.getLoanType(), result.getLoanType());
        verify(loanRepository, times(1)).save(any(ConsumerLoan.class));
    }

    @Test
    public void should_create_house_loan_successfully() {
        when(loanRepository.save(any(Loan.class))).thenReturn(houseLoan);
        Loan result = loanService.saveLoan(houseLoanProduct);

        assertNotNull(result);
        assertEquals(houseLoan.getLoanType(), result.getLoanType());
        verify(loanRepository, times(1)).save(any(HouseLoan.class));
    }

    @Test
    public void should_create_vehicle_loan_successfully() {
        when(loanRepository.save(any(Loan.class))).thenReturn(vehicleLoan);
        Loan result = loanService.saveLoan(vehicleLoanProduct);

        assertNotNull(result);
        assertEquals(vehicleLoan.getLoanType(), result.getLoanType());
        verify(loanRepository, times(1)).save(any(VehicleLoan.class));
    }
}
