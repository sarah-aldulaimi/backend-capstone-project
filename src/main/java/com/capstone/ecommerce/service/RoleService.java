package com.capstone.ecommerce.service;
import com.capstone.ecommerce.exception.AlreadyExistsException;
import com.capstone.ecommerce.exception.NotFoundException;
import com.capstone.ecommerce.model.Role;
import com.capstone.ecommerce.repository.RoleRepository;
import com.capstone.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
    public Role getRoleById(int id) {
        roleRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("This role cannot be found");
        });
        return roleRepository.getRoleById(id);
    }
    public void updateRole(int id, Role role) {
        roleRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("This role cannot be found");
        });
        role.setId(id);
        roleRepository.save(role);
    }
    public void deleteRoleById (int id){
        roleRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("This role cannot be found");
        });
        if(userRepository.findAll().stream().anyMatch(user -> user.getRoles().contains(roleRepository.getRoleById(id)))){
            throw new AlreadyExistsException("This role has been assigned to at least one user and cannot be removed!");
        }
        roleRepository.deleteById(id);
    }
    public void addRole(Role role){
        if(roleRepository.existsById(role.getId())){
            throw new AlreadyExistsException("This role already exists");
        }
        roleRepository.save(role);
    }
}
