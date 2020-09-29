package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dao;

import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
