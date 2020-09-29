package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dao;

import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
