package com.capstone.ecommerce.service;

import com.capstone.ecommerce.model.Role;
import com.capstone.ecommerce.model.Users;
import com.capstone.ecommerce.repository.RoleRepository;
import com.capstone.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public void updateUser(int id, Users users) {
        if (userRepository.existsById(id)) {
            users.setId(id);
            userRepository.save(users);
        }
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public Users addUser(Users users) {
        userRepository.save(users);
        return users;
    }

    public Users isLoginSuccessful(Users users) {
        Users temp = userRepository.getUserByEmail(users.getEmail());
        boolean successful = false;
        if (temp != null) {
            if (users.getPassword().equals(temp.getPassword())) {
                return temp;
            }
        }
        return null;
    }

    public Set<Role> checkUserRole(int userID){
        Users temp = userRepository.getUserById(userID);
        return temp.getRoles();
    }

    public Users assignUserRole(int userID, int roleID){
        Users tempUser = userRepository.getUserById(userID);
        Role tempRole = roleRepository.getRoleById(roleID);
       // Role userRole = new U()
        tempUser.getRoles().add(tempRole);
        userRepository.save(tempUser);
        return tempUser;
    }
}