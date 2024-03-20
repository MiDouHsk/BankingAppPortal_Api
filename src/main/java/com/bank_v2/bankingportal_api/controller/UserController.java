package com.bank_v2.bankingportal_api.controller;

import com.bank_v2.bankingportal_api.dto.UserDto;
import com.bank_v2.bankingportal_api.entity.User;
import com.bank_v2.bankingportal_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        try {
            if (userDto.getPassword() == null || userDto.getPassword().isEmpty() ||
                    userDto.getEmail() == null || userDto.getEmail().isEmpty() ||
                    userDto.getPhone_number() == null || userDto.getPhone_number().isEmpty() ||
                    userDto.getName() == null || userDto.getName().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            User userAccount = userService.registerUser(userDto);
            return ResponseEntity.ok(userAccount);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
        User userAccount = userService.updateUser(id, user);
        return ResponseEntity.ok(userAccount);
    }

}
