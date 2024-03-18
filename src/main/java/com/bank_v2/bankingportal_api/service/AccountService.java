package com.bank_v2.bankingportal_api.service;

import com.bank_v2.bankingportal_api.entity.Account;
import com.bank_v2.bankingportal_api.entity.User;

public interface AccountService {
    public Account createAccount(User user);
    public boolean isPinCreated(String accountNumber) ;
    public void createPIN(String accountNumber, String password, String pin) ;
    public void updatePIN(String accountNumber, String oldPIN, String password, String newPIN);
    public void cashDeposit(String accountNumber, String pin, double amount) throws ClassNotFoundException;
    public void cashWithdrawal(String accountNumber, String pin, double amount) throws ClassNotFoundException;
    public void fundTransfer(String sourceAccountNumber, String targetAccountNumber, String pin, double amount) throws ClassNotFoundException;

}
