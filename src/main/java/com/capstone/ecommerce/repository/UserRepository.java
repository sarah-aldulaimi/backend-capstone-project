package com.capstone.ecommerce.repository;

import com.capstone.ecommerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    public Users getUserById(Integer id);
    public Users getUserByEmail(String email);
}

