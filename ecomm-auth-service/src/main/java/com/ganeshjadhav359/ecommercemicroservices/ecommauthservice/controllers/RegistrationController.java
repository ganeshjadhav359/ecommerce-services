package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.controllers;

import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dao.UserRepository;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dto.UserDto;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.entities.User;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.error.ApiResponse;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.error.UserAlreadyExistException;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.services.UserServiceImpl;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/")
    public String hello(){
        return "hello";
    }
    @PostMapping("/registrations")
    public ApiResponse createAccount(@RequestBody @Valid UserDto userDto){
        //System.out.println(userDto);
        User user = userService.findUserByUsername(userDto.getUserName());
        if(user!=null)
            throw new UserAlreadyExistException("user already exits with the username: "+userDto.getUserName());

        userService.createUserAccount(userDto);

        return new ApiResponse("Registration successful...",new ArrayList<>(), HttpStatus.CREATED,new Date().toString());
    }
}
