package com.bank_v2.bankingportal_api.controller;

import com.bank_v2.bankingportal_api.config.ModelMapperConfig;
import com.bank_v2.bankingportal_api.dto.AccountDto;
import com.bank_v2.bankingportal_api.dto.AmountRequest;
import com.bank_v2.bankingportal_api.dto.FundTransferRequest;
import com.bank_v2.bankingportal_api.dto.TransactionDto;
import com.bank_v2.bankingportal_api.entity.Account;
import com.bank_v2.bankingportal_api.entity.User;
import com.bank_v2.bankingportal_api.exception.InsufficientBalanceException;
import com.bank_v2.bankingportal_api.exception.NotFoundException;
import com.bank_v2.bankingportal_api.exception.UnauthorizedException;
import com.bank_v2.bankingportal_api.mapper.AccountMapper;
import com.bank_v2.bankingportal_api.repository.AccountRepository;
import com.bank_v2.bankingportal_api.repository.UserRepository;
import com.bank_v2.bankingportal_api.service.AccountService;
import com.bank_v2.bankingportal_api.service.TransactionService;
import com.bank_v2.bankingportal_api.service.UserService;
import com.bank_v2.bankingportal_api.util.LoggedinUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.*;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    private TransactionService transactionService;

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
        String sourceAccountNumber = fundTransferRequest.getSourceAccountNumber();
        String targetAccountNumber = fundTransferRequest.getTargetAccountNumber();
        String pin = fundTransferRequest.getPin();
        double amount = fundTransferRequest.getAmount();

        if (sourceAccountNumber == null || targetAccountNumber == null || pin == null) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", "Missing account information");
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

        try {
            accountService.fundTransfer(sourceAccountNumber, targetAccountNumber, pin, amount);
            Map<String, String> response = new HashMap<>();
            response.put("msg", "Fund transferred successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", e.getMessage());
            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
        } catch (UnauthorizedException e) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", e.getMessage());
            return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
        } catch (InsufficientBalanceException e) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", e.getMessage());
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", "Internal server error");
            return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{accountNumber}/transaction")
    public ResponseEntity<List<TransactionDto>> getAllTransactionsByAccountNumber(@RequestParam String accountNumber) {
        List<TransactionDto> transactionDtos = transactionService.getAllTransactionsByAccountNumber(accountNumber);
        return ResponseEntity.ok(transactionDtos);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<TransactionDto> transactionDtos = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactionDtos);
    }
}
