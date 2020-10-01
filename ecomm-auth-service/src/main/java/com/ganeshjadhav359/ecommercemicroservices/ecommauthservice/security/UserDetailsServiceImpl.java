package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.security;

import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.entities.User;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.error.UserNotFoundException;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User  user = userService.findUserByUsername(s);
        if(user==null)
                throw new UserNotFoundException("Username: "+s+" is invalid");

        return new SecurityUser(user);
    }
}
