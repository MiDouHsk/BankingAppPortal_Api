package com.bank_v2.bankingportal_api.controller;

import com.bank_v2.bankingportal_api.dto.LoginRequest;
import com.bank_v2.bankingportal_api.dto.UserDto;
import com.bank_v2.bankingportal_api.entity.Account;
import com.bank_v2.bankingportal_api.entity.User;
import com.bank_v2.bankingportal_api.exception.NotFoundException;
import com.bank_v2.bankingportal_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bank_v2.bankingportal_api.service.AuthenticationService;

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

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        boolean authenticated = userService.login(loginRequest.getAccountNumber(), loginRequest.getPassword());
        if (authenticated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try{
            userService.DeleteUser(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
