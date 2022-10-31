package com.capstone.ecommerce.service;
import com.capstone.ecommerce.exception.AlreadyExistsException;
import com.capstone.ecommerce.exception.InvalidInputException;
import com.capstone.ecommerce.exception.NotFoundException;
import com.capstone.ecommerce.exception.userExceptions.UserDoesNotHaveARoleException;
import com.capstone.ecommerce.exception.userExceptions.UserPasswordAndEmailDontMatchException;
import com.capstone.ecommerce.model.Role;
import com.capstone.ecommerce.model.User;
import com.capstone.ecommerce.repository.LocationRepository;
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
    @Autowired
    LocationRepository locationRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(int id) {
        if(!userRepository.existsById(id)){
            throw new NotFoundException("This user cannot be found");
        }
        return userRepository.getUserById(id);
    }
    public void updateUser(int id, User user) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("This user cannot be found");
        }
        if(userRepository.getUserById(id).getId() != user.getId()){
            throw new InvalidInputException("You cannot change your ID");
        }
        if(user.getAge()<=0 || !user.getMobile().matches("[0-9]+")){
            throw new InvalidInputException("Invalid Age Input");
        }
        if(user.getLocationId()<0){
            throw new InvalidInputException("Invalid Location input");
        }
            user.setId(id);
            userRepository.save(user);
    }
    public void deleteUserById(int id) {
        if(!userRepository.existsById(id)){
            throw new NotFoundException("This user cannot be found");
        }
        userRepository.deleteById(id);
    }
    public int addUser(User user) {
        if(userRepository.existsById(user.getId()) || userRepository.existsByEmail(user.getEmail())){
            throw new AlreadyExistsException("This user already exist");
        }
        if(user.getAge()<=0 ){
            throw new InvalidInputException("Invalid Age Input");
        }
        if(!user.getMobile().matches("[0-9]+")){
            throw new InvalidInputException("Invalid Mobile input");
        }
        if(user.getLocationId()<0){
            throw new InvalidInputException("Invalid Location input");
        }
        if(!locationRepository.existsById(user.getLocationId())){
            throw new NotFoundException("This location does not exist");
        }
        userRepository.save(user);
        return user.getId();
    }
    public int isLoginSuccessful(User user) {
        if(userRepository.getUserByEmail(user.getEmail()) == null){
            throw new NotFoundException("This user cannot be found");
        }
        if(!userRepository.getUserByEmail(user.getEmail()).getPassword().equals(user.getPassword())){
            throw new UserPasswordAndEmailDontMatchException();
        }
        if(userRepository.getUserByEmail(user.getEmail()).getRoles().isEmpty()){
            throw new UserDoesNotHaveARoleException();
        }
        return userRepository.getUserByEmail(user.getEmail()).getId();
    }
    public Set<Role> checkUserRole(int userID){
        userRepository.findById(userID).orElseThrow(()->{
           throw new NotFoundException("User not found");
        });
        if(userRepository.getUserById(userID).getRoles().isEmpty()){
            throw new UserDoesNotHaveARoleException();
        }
        return userRepository.getUserById(userID).getRoles();
    }
    public void assignUserRole(int userID, int roleID){
        if(!userRepository.existsById(userID)){
            throw new NotFoundException("This user cannot be found");
        }
        if(!roleRepository.existsById(roleID)){
            throw new NotFoundException("This role cannot be found");
        }
        userRepository.getUserById(userID).getRoles().add(roleRepository.getRoleById(roleID));
        User tempUser = userRepository.getUserById(userID);
        userRepository.save(tempUser);
    }
    public void deleteUserRole(int userID, int roleID){
        if(!userRepository.existsById(userID)){
            throw new NotFoundException("This user cannot be found");
        }
        if(!roleRepository.existsById(roleID)){
            throw new NotFoundException("This role cannot be found");
        }
        userRepository.getUserById(userID).getRoles().remove(roleRepository.getRoleById(roleID));
        User tempUser = userRepository.getUserById(userID);
        userRepository.save(tempUser);
    }
}