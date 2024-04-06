package com.patika.kredinbizdeservice.service.impl;

import com.patika.kredinbizdeservice.client.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import com.patika.kredinbizdeservice.controller.model.ApplicationDto;
import com.patika.kredinbizdeservice.enums.ApplicationStatus;
import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.exceptions.BusinessException;
import com.patika.kredinbizdeservice.factory.BankServiceClient;
import com.patika.kredinbizdeservice.factory.BankServiceClientFactory;
import com.patika.kredinbizdeservice.model.*;
import com.patika.kredinbizdeservice.producer.NotificationProducer;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.producer.enums.NotificationType;
import com.patika.kredinbizdeservice.repository.ApplicationRepository;
import com.patika.kredinbizdeservice.service.IApplicationService;
import com.patika.kredinbizdeservice.service.ICreditCardService;
import com.patika.kredinbizdeservice.service.ILoanService;
import com.patika.kredinbizdeservice.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.patika.kredinbizdeservice.exceptions.ExceptionMessages.APPLICATION_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService implements IApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ICreditCardService creditCardService;
    private final ILoanService loanService;
    private final IUserService userService;
    private final BankServiceClientFactory bankServiceClientFactory;
    private final NotificationProducer notificationProducer;

    @Override
    public Application saveApplication(ApplicationDto applicationDto) {
        User user = userService.getById(applicationDto.getUser().getId());
        String bankName;
        Long productId;
        Application application;
        if (applicationDto.getProduct().getType().equals(LoanType.CREDIT_CARD)) {
            CreditCard creditCard = creditCardService.getById(applicationDto.getProduct().getId());
            productId = creditCard.getId();
            bankName = creditCard.getBank().getName();
            Application savedApplication1 = new Application();

            savedApplication1.setUser(user);
            savedApplication1.setCreditCard(creditCard);
            savedApplication1.setLocalDateTime(applicationDto.getLocalDateTime());
            savedApplication1.setApplicationStatus(ApplicationStatus.INITIAL);

            application = applicationRepository.save(savedApplication1);
        } else {
            Loan loan = loanService.getLoanById(applicationDto.getProduct().getId());
            productId = loan.getId();
            bankName = loan.getBank().getName();
            Application savedApplication2 = new Application();

            savedApplication2.setUser(user);
            if (applicationDto.getProduct().getType().equals(LoanType.CONSUMER_LOAN)) {
                savedApplication2.setConsumerLoan((ConsumerLoan) loan);
            } else if (applicationDto.getProduct().getType().equals(LoanType.HOUSE_LOAN)) {
                savedApplication2.setHouseLoan((HouseLoan) loan);
            } else {
                savedApplication2.setVehicleLoan((VehicleLoan) loan);
            }
            savedApplication2.setLocalDateTime(applicationDto.getLocalDateTime());
            savedApplication2.setApplicationStatus(ApplicationStatus.INITIAL);
            application = applicationRepository.save(savedApplication2);
        }

        log.info("Bank name: " + bankName);
        BankServiceClient bankServiceClient = bankServiceClientFactory.getBankServiceClient(bankName);
        log.info("Bank Service Client is " + bankServiceClient.toString());
        ApplicationResponse response = bankServiceClient.createApplication(ApplicationRequest.builder()
                .userId(user.getId())
                .productId(productId)
                .build());
        log.info("Application sent to bank. Current status is: " + (response != null ? response.getApplicationStatus() : ""));

        notificationProducer.sendNotification(NotificationDTO.builder()
                .to(user.getEmail())
                .notificationType(NotificationType.EMAIL)
                .message("Başvurunuz alınmıstır!")
                .build());

        return application;
    }

    @Override
    public List<ApplicationResponse> getApplicationsByUserId(Long userId) {
        BankServiceClient bankServiceClient = bankServiceClientFactory.getBankServiceClient("Garanti");
        log.info("Applications found for user");
        return bankServiceClient.getApplicationsByUserId(userId);
    }

    @Override
    public List<Application> getAllApplications(LoanType type) {
        if (type == null) {
            return applicationRepository.findAll();
        } else if (type == LoanType.CREDIT_CARD) {
            return applicationRepository.findAll().stream().filter(f -> f.getCreditCard() != null).collect(Collectors.toList());
        } else {
            return applicationRepository.findAll().stream().filter(f -> f.getConsumerLoan().getLoanType() == type || f.getHouseLoan().getLoanType() == type || f.getVehicleLoan().getLoanType() == type).collect(Collectors.toList());
        }
    }

    @Override
    public Application getApplicationById(Long id) {
        Optional<Application> applicationOpt = applicationRepository.findById(id);
        return applicationOpt.orElseThrow(() -> new BusinessException(APPLICATION_NOT_FOUND_EXCEPTION_MESSAGE));
    }
}
