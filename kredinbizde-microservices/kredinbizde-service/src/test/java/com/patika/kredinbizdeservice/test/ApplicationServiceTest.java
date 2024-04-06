package com.patika.kredinbizdeservice.test;

import com.patika.kredinbizdeservice.client.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import com.patika.kredinbizdeservice.controller.model.ApplicationDto;
import com.patika.kredinbizdeservice.controller.model.ProductDto;
import com.patika.kredinbizdeservice.controller.model.UserDto;
import com.patika.kredinbizdeservice.enums.ApplicationStatus;
import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.enums.SectorType;
import com.patika.kredinbizdeservice.factory.BankServiceClient;
import com.patika.kredinbizdeservice.factory.BankServiceClientFactory;
import com.patika.kredinbizdeservice.model.*;
import com.patika.kredinbizdeservice.producer.NotificationProducer;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.repository.ApplicationRepository;
import com.patika.kredinbizdeservice.service.impl.ApplicationService;
import com.patika.kredinbizdeservice.service.impl.CreditCardService;
import com.patika.kredinbizdeservice.service.impl.LoanService;
import com.patika.kredinbizdeservice.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {
    @InjectMocks
    private ApplicationService applicationService;
    @Mock
    private NotificationProducer notificationProducer;
    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private LoanService loanService;
    @Mock
    private CreditCardService creditCardService;
    @Mock
    private BankServiceClientFactory bankServiceClientFactory;
    @Mock
    private UserService userService;
    private Application application;
    private ApplicationDto creditCardApplication;
    private ApplicationDto loanApplication;
    private Application savedApplication;
    private User user;
    private CreditCard creditCard;
    private ConsumerLoan loan;
    private ApplicationResponse applicationResponse;

    @BeforeEach
    void setUp() {
        Bank bank = new Bank();
        bank.setId(1L);
        bank.setName("Test Bank");

        UserDto userDto = UserDto.builder()
                .id(1L)
                .name("test")
                .surname("test surname")
                .email("test@gmail.com")
                .password("password")
                .build();

        user = new User();
        user.setId(1L);
        user.setName("test");
        user.setSurname("test surname");
        user.setEmail("test@gmail.com");
        user.setPassword("password");

        Campaign campaign =new Campaign();
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


        ProductDto creditCardProduct = ProductDto.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(0))
                .installment(3)
                .interestRate(1.2)
                .title("Test")
                .type(LoanType.CREDIT_CARD)
                .build();

        ProductDto loanProduct = ProductDto.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(0))
                .installment(3)
                .interestRate(1.2)
                .title("Test")
                .type(LoanType.CONSUMER_LOAN)
                .build();

        creditCardApplication = ApplicationDto.builder()
                .id(1L)
                .user(userDto)
                .product(creditCardProduct)
                .localDateTime(LocalDateTime.now())
                .build();

        loanApplication = ApplicationDto.builder()
                .id(1L)
                .user(userDto)
                .product(loanProduct)
                .localDateTime(LocalDateTime.now())
                .build();

        loan = new ConsumerLoan();
        loan.setId(1L);
        loan.setAmount(BigDecimal.valueOf(1000));
        loan.setInstallment(12);
        loan.setInterestRate(1.5);
        loan.setTitle("test");
        loan.setBank(bank);

        application = new Application();
        application.setId(1L);
        application.setApplicationStatus(ApplicationStatus.INITIAL);
        application.setUser(user);
        application.setLocalDateTime(LocalDateTime.now());
        application.setCreditCard(creditCard);
        application.setConsumerLoan(loan);

        savedApplication = new Application();
        savedApplication.setId(1L);
        savedApplication.setApplicationStatus(ApplicationStatus.INITIAL);
        savedApplication.setUser(user);
        savedApplication.setLocalDateTime(LocalDateTime.now());
        savedApplication.setCreditCard(creditCard);
        savedApplication.setConsumerLoan(loan);

        applicationResponse = ApplicationResponse.builder()
                .id(1L)
                .applicationStatus(com.patika.kredinbizdeservice.client.dto.response.ApplicationStatus.INITIAL)
                .createDate(LocalDateTime.now())
                .productId(1L)
                .userId(1L)
                .build();


    }

    @Test
    public void should_save_creditCard_application_successfully() {
        when(userService.getById(anyLong())).thenReturn(user);
        when(creditCardService.getById(anyLong())).thenReturn(creditCard);
        when(applicationRepository.save(any(Application.class))).thenReturn(savedApplication);
        BankServiceClient bankServiceClient = Mockito.mock(BankServiceClient.class);
        when(bankServiceClientFactory.getBankServiceClient(anyString())).thenReturn(bankServiceClient);
        when(bankServiceClient.createApplication(any(ApplicationRequest.class))).thenReturn(applicationResponse);

        Application result = applicationService.saveApplication(creditCardApplication);

        assertNotNull(result);
        assertEquals(savedApplication.getId(), result.getId());
        assertEquals(savedApplication.getUser().getId(), result.getUser().getId());
        assertEquals(savedApplication.getCreditCard().getId(), result.getCreditCard().getId());

        verify(userService, times(1)).getById(anyLong());
        verify(applicationRepository, times(1)).save(any(Application.class));
        verify(bankServiceClientFactory, times(1)).getBankServiceClient(anyString());
        verify(bankServiceClient, times(1)).createApplication(any(ApplicationRequest.class));
        verify(notificationProducer, times(1)).sendNotification(any(NotificationDTO.class));
    }

    @Test
    public void should_save_loan_application_successfully() {
        when(userService.getById(anyLong())).thenReturn(user);
        when(loanService.getLoanById(anyLong())).thenReturn(loan);
        when(applicationRepository.save(any(Application.class))).thenReturn(savedApplication);
        BankServiceClient bankServiceClient = Mockito.mock(BankServiceClient.class);
        when(bankServiceClientFactory.getBankServiceClient(anyString())).thenReturn(bankServiceClient);
        when(bankServiceClient.createApplication(any(ApplicationRequest.class))).thenReturn(applicationResponse);

        Application result = applicationService.saveApplication(loanApplication);

        assertNotNull(result);
        assertEquals(savedApplication.getId(), result.getId());
        assertEquals(savedApplication.getUser().getId(), result.getUser().getId());
        assertEquals(savedApplication.getConsumerLoan().getId(), result.getConsumerLoan().getId());

        verify(userService, times(1)).getById(anyLong());
        verify(applicationRepository, times(1)).save(any(Application.class));
        verify(bankServiceClientFactory, times(1)).getBankServiceClient(anyString());
        verify(bankServiceClient, times(1)).createApplication(any(ApplicationRequest.class));
        verify(notificationProducer, times(1)).sendNotification(any(NotificationDTO.class));
    }


    @Test
    public void should_get_applications_by_user_id_successfully() {

        List<ApplicationResponse> applicationResponses = Collections.singletonList(applicationResponse);

        BankServiceClient bankServiceClient = Mockito.mock(BankServiceClient.class);
        when(bankServiceClient.getApplicationsByUserId(anyLong())).thenReturn(applicationResponses);
        when(bankServiceClientFactory.getBankServiceClient(anyString())).thenReturn(bankServiceClient);
        List<ApplicationResponse> result = applicationService.getApplicationsByUserId(1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(applicationResponses.get(0).getId(), result.get(0).getId());

        verify(bankServiceClientFactory, times(1)).getBankServiceClient(anyString());
        verify(bankServiceClient, times(1)).getApplicationsByUserId(anyLong());
    }

    @Test
    public void should_get_all_applications_when_type_is_null() {

        List<Application> allApplications = Collections.singletonList(application);
        when(applicationRepository.findAll()).thenReturn(allApplications);

        List<Application> result = applicationService.getAllApplications(null);

        assertNotNull(result);
        assertEquals(allApplications.size(), result.size());
        assertEquals(allApplications, result);

        verify(applicationRepository, times(1)).findAll();
    }

    @Test
    public void should_get_applications_by_type() {

        List<Application> allApplications = Collections.singletonList(application);
        when(applicationRepository.findAll()).thenReturn(allApplications);

        List<Application> result = applicationService.getAllApplications(LoanType.CREDIT_CARD);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(LoanType.CREDIT_CARD, result.get(0).getCreditCard().getLoanType());

        verify(applicationRepository, times(1)).findAll();
    }


}
