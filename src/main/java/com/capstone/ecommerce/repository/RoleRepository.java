package com.capstone.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.ecommerce.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role getRoleById(Integer id);
}

