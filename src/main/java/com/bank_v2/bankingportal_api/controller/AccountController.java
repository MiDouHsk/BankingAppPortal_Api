package com.bank_v2.bankingportal_api.controller;

import com.bank_v2.bankingportal_api.dto.AmountRequest;
import com.bank_v2.bankingportal_api.dto.FundTransferRequest;
import com.bank_v2.bankingportal_api.entity.Account;
import com.bank_v2.bankingportal_api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.lang.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/deposit")
    public ResponseEntity<?> cashDeposit(@RequestBody AmountRequest amountRequest) throws ClassNotFoundException {
        if (amountRequest.getAmount() <= 0) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", "Invalid amount");
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        accountService.cashDeposit(amountRequest.getAccountNumber(), amountRequest.getPin(),amountRequest.getAmount());

        Map<String, String> response = new HashMap<>();
        response.put("msg", "Cash deposited successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> cashWithdrawal(@RequestBody AmountRequest amountRequest) throws ClassNotFoundException {
        if(amountRequest.getAmount() <= 0) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", "Invalid amount");
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        accountService.cashWithdrawal(amountRequest.getAccountNumber(), amountRequest.getPin()
                , amountRequest.getAmount());
            Map<String, String> response = new HashMap<>();
            response.put("msg", "Cash withdrawn successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/fund-transfer")
    public ResponseEntity<?> fundTransfer(@RequestBody FundTransferRequest fundTransferRequest) {
        if (fundTransferRequest.getAmount() <= 0) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", "Invalid amount");
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        Account account = new Account();

        accountService.fundTransfer(account.getAccountNumber(), fundTransferRequest.getTargetAccountNumber(),
                fundTransferRequest.getPin(), fundTransferRequest.getAmount());
        Map<String, String> response = new HashMap<>();
        response.put("msg", "Fund transferred successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
