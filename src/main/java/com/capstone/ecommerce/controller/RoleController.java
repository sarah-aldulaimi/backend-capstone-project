package com.capstone.ecommerce.controller;

import com.capstone.ecommerce.model.Role;
import com.capstone.ecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private void deleteRole(@PathVariable("roleID") int roleID) {
        roleService.deleteRoleById(roleID);
    }

    @PostMapping
    private void saveRole(@RequestBody Role role) {
        roleService.addRole(role);
    }

    @PutMapping("/{roleID}")
    private void updateRole(@PathVariable("roleID") int roleID, @RequestBody Role role){
        roleService.updateRole(roleID,role);
    }

}
