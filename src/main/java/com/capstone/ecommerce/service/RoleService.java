package com.capstone.ecommerce.service;

import com.capstone.ecommerce.model.Role;
import com.capstone.ecommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public Role getRoleById(int id) {
        return roleRepository.getRoleById(id);
    }

    public void updateRole(int id, Role role) {
        if (roleRepository.existsById(id)) {
            role.setId(id);
            roleRepository.save(role);
        }
    }
    public void deleteRoleById (int id){
        roleRepository.deleteById(id);
    }

    public void addRole(Role role){
        roleRepository.save(role);
    }
}
