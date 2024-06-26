package com.patika.kredinbizdeservice.test;

import com.patika.kredinbizdeservice.controller.model.BankDto;
import com.patika.kredinbizdeservice.controller.model.ProductDto;
import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.enums.VehicleStatusType;
import com.patika.kredinbizdeservice.model.*;
import com.patika.kredinbizdeservice.repository.ConsumerLoanRepository;
import com.patika.kredinbizdeservice.repository.HouseLoanRepository;
import com.patika.kredinbizdeservice.repository.VehicleLoanRepository;
import com.patika.kredinbizdeservice.service.impl.BankService;
import com.patika.kredinbizdeservice.service.impl.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    private ConsumerLoanRepository consumerLoanRepository;
    @Mock
    private HouseLoanRepository houseLoanRepository;
    @Mock
    private VehicleLoanRepository vehicleLoanRepository;
    private ProductDto consumerLoanProduct;
    private ProductDto houseLoanProduct;
    private ProductDto vehicleLoanProduct;
    private ConsumerLoan consumerLoan;
    private HouseLoan houseLoan;
    private VehicleLoan vehicleLoan;
    private BankDto bankDto;

    @BeforeEach
    void setUp() {

        Bank bank = new Bank();
        bank.setId(1L);
        bank.setName("Test Bank");

        bankDto = BankDto.builder()
                .name("Test bank")
                .id(1L)
                .build();

        BankService bankService = Mockito.mock(BankService.class);
        when(bankService.getBankById(anyLong())).thenReturn(bank);

        loanService = new LoanService(consumerLoanRepository, vehicleLoanRepository, houseLoanRepository, bankService);

        consumerLoanProduct = ProductDto.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(0))
                .installment(3)
                .interestRate(1.2)
                .title("Test")
                .type(LoanType.CONSUMER_LOAN)
                .bank(bankDto)
                .build();

        houseLoanProduct = ProductDto.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(0))
                .installment(3)
                .interestRate(1.2)
                .title("Test")
                .type(LoanType.HOUSE_LOAN)
                .bank(bankDto)
                .build();

        vehicleLoanProduct = ProductDto.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(0))
                .installment(3)
                .interestRate(1.2)
                .title("Test")
                .type(LoanType.VEHICLE_LOAN)
                .bank(bankDto)
                .vehicleStatusType(VehicleStatusType.NEW)
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
        vehicleLoan.setVehicleStatusType(VehicleStatusType.NEW);
        vehicleLoan.setBank(bank);
    }

    @Test
    public void should_create_consumer_loan_successfully() {
        when(consumerLoanRepository.save(any(ConsumerLoan.class))).thenReturn(consumerLoan);
        Loan result = loanService.saveLoan(consumerLoanProduct);

        assertNotNull(result);
        assertEquals(consumerLoan.getLoanType(), result.getLoanType());
        verify(consumerLoanRepository, times(1)).save(any(ConsumerLoan.class));
    }

    @Test
    public void should_create_house_loan_successfully() {
        when(houseLoanRepository.save(any(HouseLoan.class))).thenReturn(houseLoan);
        Loan result = loanService.saveLoan(houseLoanProduct);

        assertNotNull(result);
        assertEquals(houseLoan.getLoanType(), result.getLoanType());
        verify(houseLoanRepository, times(1)).save(any(HouseLoan.class));
    }

    @Test
    public void should_create_vehicle_loan_successfully() {
        when(vehicleLoanRepository.save(any(VehicleLoan.class))).thenReturn(vehicleLoan);
        Loan result = loanService.saveLoan(vehicleLoanProduct);

        assertNotNull(result);
        assertEquals(vehicleLoan.getLoanType(), result.getLoanType());
        verify(vehicleLoanRepository, times(1)).save(any(VehicleLoan.class));
    }
}
