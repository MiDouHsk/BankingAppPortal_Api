package com.bank_v2.bankingportal_api.service;

import com.bank_v2.bankingportal_api.entity.Account;
import com.bank_v2.bankingportal_api.entity.User;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    Account createAccount(User user);
    void cashDeposit(String accountNumber, String pin, double amount) throws ClassNotFoundException;
    void cashWithdrawal(String accountNumber, String pin, double amount) throws ClassNotFoundException;
    void fundTransfer(String sourceAccountNumber, String targetAccountNumber, String pin, double amount);

    void deleteAccountsByUserId(Long id);

    void deleteAccountWithTransactions(Long id);
}
