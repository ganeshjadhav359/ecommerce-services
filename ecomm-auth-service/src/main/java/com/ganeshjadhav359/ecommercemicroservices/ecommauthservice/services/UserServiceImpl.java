package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.services;

import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dao.RoleRepository;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dao.UserRepository;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dto.UserDto;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.entities.Role;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.entities.User;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.error.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<Role> findRolesByRoleIds(List<Integer> roleIds) {
        List<Role> roles = new ArrayList<>();
        for(Integer id : roleIds){
            roles.add(roleRepository.findById(id).orElseThrow(()-> new RoleNotFoundException("provided role id "+id+" is not correct")));
        }
        return roles;
    }

    @Override
    public void createUserAccount(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(true);
        Set<Integer> roleIds = new HashSet<>();
        roleIds.add(1);  // user_role is assigned to all customers/users
        if(userDto.getRoleIds()!=null){
          roleIds.addAll(userDto.getRoleIds());
        }
        List<Role> roles = findRolesByRoleIds(new ArrayList<>(roleIds));
        user.setRoles(roles);
        
        userRepository.save(user);
    }
}
