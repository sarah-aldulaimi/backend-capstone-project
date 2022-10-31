package com.capstone.ecommerce.repository;

import com.capstone.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User getUserById(Integer id);
    public User getUserByEmail(String email);

    public boolean existsByEmail(String email);
}

