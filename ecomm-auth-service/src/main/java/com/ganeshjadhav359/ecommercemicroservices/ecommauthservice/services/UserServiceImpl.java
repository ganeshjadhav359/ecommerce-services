package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.services;

import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dao.RoleRepository;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dao.UserRepository;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dto.UserDto;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.entities.Role;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUserName(username);

    }

    @Override
    public void createUserAccount(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(true);
        Set<Integer> roleIds = new HashSet<>();
        roleIds.add(1);  // user_role is assigned to all customers/users
        if(user.getRoles()!=null){
          roleIds.addAll(userDto.getRoleIds());
        }
        List<Role> roles = roleRepository.findAllById(roleIds);
        user.setRoles(roles);
        
        userRepository.save(user);
    }
}
