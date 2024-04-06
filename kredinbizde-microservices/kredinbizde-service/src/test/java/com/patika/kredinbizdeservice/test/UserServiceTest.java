package com.patika.kredinbizdeservice.test;

import com.patika.kredinbizdeservice.controller.model.UserDto;
import com.patika.kredinbizdeservice.enums.ApplicationStatus;
import com.patika.kredinbizdeservice.exceptions.BusinessException;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.producer.NotificationProducer;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.repository.ApplicationRepository;
import com.patika.kredinbizdeservice.repository.UserRepository;
import com.patika.kredinbizdeservice.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private NotificationProducer notificationProducer;
    @Mock
    private ApplicationRepository applicationRepository;

    private UserDto userDto;
    private User user;
    private Application application;

    @BeforeEach
    void setUp() {
        userDto = UserDto.builder()
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

        application = new Application();
        application.setId(1L);
        application.setApplicationStatus(ApplicationStatus.INITIAL);
        application.setUser(user);
    }

    @Test
    public void should_create_user_successfully() {
        //hangi durumda hangi mock datasının dönceği bilgisi
        when(userRepository.save(any(User.class))).thenReturn(user);
        //test edilecek metod çağrımı
        userService.save(userDto);
        assertEquals(user.getId(), 1L);
        //verify
        verify(userRepository, times(1)).save(any(User.class));
        verify(notificationProducer, times(1)).sendNotification(any(NotificationDTO.class));
    }

    @Test
    public void should_get_all_users() {
        List<User> userList = Collections.singletonList(user);
        when(userRepository.findAll()).thenReturn(userList);
        List<User> result = userService.getAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(user.getId(), result.get(0).getId());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void should_throw_exception_when_getting_user_by_invalid_email() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> userService.getByEmail("invalid@gmail.com"));
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    public void should_update_user_successfully() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        User updatedUser = userService.update(1L, userDto);
        assertEquals(user.getId(), updatedUser.getId());
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    public void should_get_user_by_id() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        User result = userService.getById(1L);
        assertEquals(user.getId(), result.getId());
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    public void should_throw_exception_when_getting_user_by_invalid_id() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> userService.getById(999L));
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    public void should_get_user_applications() {
        when(applicationRepository.findAll()).thenReturn(Collections.singletonList(application));
        List<Application> result = userService.getUserApplications("test@gmail.com");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(applicationRepository, times(1)).findAll();
    }

    @Test
    public void should_return_empty_list_when_no_user_applications_found() {
        when(applicationRepository.findAll()).thenReturn(Collections.emptyList());
        List<Application> result = userService.getUserApplications("test@gmail.com");
        assertTrue(result.isEmpty());
        verify(applicationRepository, times(1)).findAll();
    }
}
