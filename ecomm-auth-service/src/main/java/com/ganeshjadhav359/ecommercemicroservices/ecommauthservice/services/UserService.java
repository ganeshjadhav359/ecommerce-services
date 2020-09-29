package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.services;

import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dto.UserDto;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.entities.User;

public interface UserService {
    User findUserByUsername(String username);
    void createUserAccount(UserDto userDto);
    
}
