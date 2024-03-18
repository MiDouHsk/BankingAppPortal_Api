package com.bank_v2.bankingportal_api.controller;

import com.bank_v2.bankingportal_api.dto.AccountDto;
import com.bank_v2.bankingportal_api.dto.UserDto;
import com.bank_v2.bankingportal_api.entity.User;
import com.bank_v2.bankingportal_api.service.DashboardService;
import com.bank_v2.bankingportal_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestParam("accountNumber")
                                                      String accountNumber) throws ClassNotFoundException {
        try{
            UserDto userDto = dashboardService.getUserDetails(accountNumber);
            return ResponseEntity.ok(userDto);
        }catch (ClassNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/Account")
    public ResponseEntity<?> getAccountDetail( @RequestParam String userNumber) {
        try {
            AccountDto accountDto = dashboardService.getAccountDetails(userNumber);
            return ResponseEntity.ok(accountDto);
        } catch (ClassNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

