package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.controller.model.UserDto;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.User;

import java.util.List;

public interface IUserService {
    User save(UserDto dto);

    List<User> getAll();

    User getByEmail(String email);

    User update(Long id, UserDto userDto);

    User getById(Long id);

    List<Application> getUserApplications(String email);


}
