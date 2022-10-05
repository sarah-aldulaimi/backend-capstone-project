package com.capstone.ecommerce.service;

import com.capstone.ecommerce.model.Users;
import com.capstone.ecommerce.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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
}