package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.controller.model.ApiResponse;
import com.patika.kredinbizdeservice.controller.model.UserDto;
import com.patika.kredinbizdeservice.service.IUserService;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/kredinbizde")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/users")
    public ResponseEntity<ApiResponse<User>> create(@RequestBody UserDto dto) {
        User savedUser = userService.save(dto);
        ApiResponse<User> apiResponse = ApiResponse.<User>builder()
                .data(savedUser)
                .message("User Created")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<User>>> getAll() {
        List<User> allUsers = userService.getAll();
        ApiResponse<List<User>> apiResponse = ApiResponse.<List<User>>builder()
                .data(allUsers)
                .message("Users found")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<ApiResponse<User>> getByEmail(@PathVariable String email) {
        User user = userService.getByEmail(email);
        ApiResponse<User> apiResponse = ApiResponse.<User>builder()
                .data(user)
                .message("User found by given email")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<User>> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        ApiResponse<User> apiResponse = ApiResponse.<User>builder()
                .data(user)
                .message("User found by given id")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @GetMapping("/users/applications")
    public ResponseEntity<ApiResponse<List<Application>>> getUserApplicationsByEmail(@RequestParam String email) {
        List<Application> applicationList = userService.getUserApplications(email);
        ApiResponse<List<Application>> apiResponse = ApiResponse.<List<Application>>builder()
                .data(applicationList)
                .message("Applications found for user by given email")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<ApiResponse<User>> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        User updatedUser = userService.update(id, userDto);
        ApiResponse<User> apiResponse = ApiResponse.<User>builder()
                .data(updatedUser)
                .message("User updated by given id")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
}
