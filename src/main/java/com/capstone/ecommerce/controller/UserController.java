package com.capstone.ecommerce.controller;
import com.capstone.ecommerce.model.Role;
import com.capstone.ecommerce.model.User;
import com.capstone.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/users")
    private List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/users/{userID}")
    private User getUserByID(@PathVariable("userID") int userID) {
        return userService.getUserById(userID);
    }
    @DeleteMapping("/users/{userID}")
    private ResponseEntity deleteUser(@PathVariable("userID") int userID) {
        userService.deleteUserById(userID);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/users")
    private ResponseEntity saveUser(@RequestBody User user) {
        int id = userService.addUser(user);
        return new ResponseEntity(id,HttpStatus.OK);
    }
    @PutMapping("/users/{userID}")
    private ResponseEntity<?> updateUser(@PathVariable("userID") int userID, @RequestBody User user){
        userService.updateUser(userID, user);
        return ResponseEntity.ok().build();
//        return new ResponseEntity(userID, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        int id = userService.isLoginSuccessful(user);
        return new ResponseEntity(id,HttpStatus.CREATED);
    }
    @GetMapping("users/{userID}/roles")
    public Set<Role> checkUserRole(@PathVariable("userID") int userID){
        return userService.checkUserRole(userID);
    }
    @PostMapping("users/{userID}/roles")
    public ResponseEntity assignUserRole(@PathVariable("userID") int userID, @RequestBody Role role){
        userService.assignUserRole(userID,role.getId());
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @DeleteMapping("users/{userID}/roles/{roleID}")
    public ResponseEntity deleteUserRole(@PathVariable("userID") int userID, @PathVariable("roleID") int roleID){
        userService.deleteUserRole(userID,roleID);
        return new ResponseEntity(HttpStatus.OK);
    }
}
