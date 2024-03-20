package com.bank_v2.bankingportal_api.service;

import com.bank_v2.bankingportal_api.entity.Account;
import com.bank_v2.bankingportal_api.entity.User;

public interface AccountService {
    public Account createAccount(User user);
    public void cashDeposit(String accountNumber, String pin, double amount) throws ClassNotFoundException;
    public void cashWithdrawal(String accountNumber, String pin, double amount) throws ClassNotFoundException;
    public void fundTransfer(String sourceAccountNumber, String targetAccountNumber, String pin, double amount);
    void deleteAccount(Long accountId);

}
