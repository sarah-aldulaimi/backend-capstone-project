package com.capstone.ecommerce.controller;
import com.capstone.ecommerce.model.Role;
import com.capstone.ecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    RoleService roleService;
    @GetMapping
    private List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }
    @GetMapping("/{roleID}")
    private Role getRoleByID(@PathVariable("roleID") int roleID) {
        return roleService.getRoleById(roleID);
    }
    @DeleteMapping("/{roleID}")
    private ResponseEntity deleteRole(@PathVariable("roleID") int roleID) {
        roleService.deleteRoleById(roleID);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping
    private ResponseEntity saveRole(@RequestBody Role role) {
        roleService.addRole(role);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping("/{roleID}")
    private ResponseEntity updateRole(@PathVariable("roleID") int roleID, @RequestBody Role role){
        roleService.updateRole(roleID,role);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
