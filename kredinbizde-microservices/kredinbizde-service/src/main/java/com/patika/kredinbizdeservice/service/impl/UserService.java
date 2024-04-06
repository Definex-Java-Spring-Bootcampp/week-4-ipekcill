package com.patika.kredinbizdeservice.service.impl;

import com.patika.kredinbizdeservice.controller.model.AddressDto;
import com.patika.kredinbizdeservice.controller.model.UserDto;
import com.patika.kredinbizdeservice.exceptions.BusinessException;
import com.patika.kredinbizdeservice.mapper.ModelMapper;
import com.patika.kredinbizdeservice.model.Address;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.producer.NotificationProducer;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.producer.enums.NotificationType;
import com.patika.kredinbizdeservice.repository.ApplicationRepository;
import com.patika.kredinbizdeservice.repository.UserRepository;
import com.patika.kredinbizdeservice.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.patika.kredinbizdeservice.exceptions.ExceptionMessages.USER_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final NotificationProducer notificationProducer;
    private final ModelMapper modelMapper = ModelMapper.INSTANCE;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = BusinessException.class)
    public User save(UserDto dto) {
        dto.setAddress(getAddress());

        notificationProducer.sendNotification(NotificationDTO.builder()
                .to(dto.getEmail())
                .notificationType(NotificationType.EMAIL)
                .message("Kullanıcı kaydı oluşturuldu")
                .build());
        return userRepository.save(modelMapper.toUser(dto));
    }

    private Address getAddress() {
        AddressDto address = AddressDto.builder()
                .addressTitle("Ev")
                .province("Izmir")
                .build();
        return modelMapper.toAddress(address);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.orElseThrow(() -> new BusinessException("User not found by given email"));
    }

    @Override
    public User update(Long id, UserDto userDto) {
        User user = modelMapper.toUser(userDto);
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User existUser = userOpt.get();
            existUser.setName(user.getName());
            existUser.setSurname(user.getSurname());
            existUser.setEmail(user.getEmail());
            existUser.setPassword(user.getPassword());
            existUser.setIsActive(user.getIsActive());
            existUser.setPhoneNumber(user.getPhoneNumber());
            return userOpt.get();
        } else {
            throw new BusinessException(USER_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public User getById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.orElseThrow(() -> new BusinessException(USER_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public List<Application> getUserApplications(String email) {
        return applicationRepository.findAll().stream()
                .filter(application -> application.getUser().getEmail().equals(email))
                .collect(Collectors.toList());
    }


}
