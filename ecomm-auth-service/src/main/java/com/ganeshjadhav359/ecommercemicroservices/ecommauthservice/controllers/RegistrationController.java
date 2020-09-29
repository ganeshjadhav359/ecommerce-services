package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.controllers;

import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dto.UserDto;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    @GetMapping("/")
    public String hello(){
        return "hello";
    }
    @PostMapping("/registrations")
    public UserDto createAccount(@RequestBody @Valid UserDto user){
        System.out.println(user);
        return user;
    }
}
