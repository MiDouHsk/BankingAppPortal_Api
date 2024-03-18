package com.bank_v2.bankingportal_api.controller;

import com.bank_v2.bankingportal_api.dto.UserDto;
import com.bank_v2.bankingportal_api.entity.User;
import com.bank_v2.bankingportal_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto user) {
        if (user.getPassword() == null || user.getPassword().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPhone_number() == null || user.getPhone_number().isEmpty() ||
                user.getName() == null || user.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        User userAccount = userService.registerUser(user);
        return ResponseEntity.ok(userAccount);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
        User userAccount = userService.updateUser(id, user);

        return ResponseEntity.ok(userAccount);
    }

}
