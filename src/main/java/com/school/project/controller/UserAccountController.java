package com.school.project.controller;

import com.school.project.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @PutMapping("/usersAccounts/{userId}/role/{roleId}")
    public ResponseEntity updateAccountRole(@PathVariable Long userId, @PathVariable Integer roleId){
        userAccountService.updateAccountRole(userId, roleId);
        return ResponseEntity.ok().build();
    }
}
