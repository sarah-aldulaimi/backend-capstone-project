package com.capstone.ecommerce.controller;

import com.capstone.ecommerce.model.Users;
import com.capstone.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    private List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userID}")
    private Users getUserByID(@PathVariable("userID") int userID) {
        return userService.getUserById(userID);
    }

    @DeleteMapping("/users/{userID}")
    private void deleteUser(@PathVariable("userID") int userID) {
        userService.deleteUserById(userID);
    }

    @PostMapping("/users")
    private Users saveUser(@RequestBody Users users) {
        return userService.addUser(users);
    }

    @PutMapping("/users/{userID}")
    private void updateUser(@PathVariable("userID") int userID, @RequestBody Users users){
        userService.updateUser(userID, users);
    }
    @CrossOrigin(origins = "https://localhost:8080")
    @PostMapping("/login")
    public Users login(@RequestBody Users users){
        return userService.isLoginSuccessful(users);
    }
}
