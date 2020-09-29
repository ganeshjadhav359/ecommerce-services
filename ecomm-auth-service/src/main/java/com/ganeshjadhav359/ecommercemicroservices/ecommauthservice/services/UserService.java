package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.services;

import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dto.UserDto;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.entities.Role;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.entities.User;

import java.util.List;

public interface UserService {
    User findUserByUsername(String username);
    void createUserAccount(UserDto userDto);
    List<Role> findRolesByRoleIds(List<Integer> roleIds);
    
}
