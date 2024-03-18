package com.bank_v2.bankingportal_api.service.serviceImpl;

import com.bank_v2.bankingportal_api.entity.Account;
import com.bank_v2.bankingportal_api.entity.TransactionType;
import com.bank_v2.bankingportal_api.entity.User;
import com.bank_v2.bankingportal_api.exception.InsufficientBalanceException;
import com.bank_v2.bankingportal_api.exception.NotFoundException;
import com.bank_v2.bankingportal_api.exception.UnauthorizedException;
import com.bank_v2.bankingportal_api.repository.AccountRepository;
import com.bank_v2.bankingportal_api.service.AccountService;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(User user) {
        String accountNumber =generateUniqueAccountNumber();
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(0.0);
        return accountRepository.save(account);
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            // Generate a UUID as the account number
            accountNumber = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
        } while (accountRepository.findByAccountNumber(accountNumber) != null);

        return accountNumber;
    }

    @Override
    public boolean isPinCreated(String accountNumber) {
        return false;
    }

    @Override
    public void createPIN(String accountNumber, String password, String pin) {

    }

    @Override
    public void updatePIN(String accountNumber, String oldPIN, String password, String newPIN) {

    }

    @Override
    public void cashDeposit(String accountNumber, String pin, double amount) throws ClassNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new ClassNotFoundException("Account not found");
        }
        double getBalance = account.getBalance();
        double newBalance = getBalance + amount;
        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    @Override
    public void cashWithdrawal(String accountNumber, String pin, double amount) throws ClassNotFoundException {
        Account account =accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new ClassNotFoundException("Account not found");
        }

        double currenBalance =account.getBalance();
        if (currenBalance < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        double newBalance = currenBalance - amount;
        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    @Override
    public void fundTransfer(String sourceAccountNumber, String targetAccountNumber, String pin, double amount) throws ClassNotFoundException {
        Account sourceAccount = accountRepository.findByAccountNumber(sourceAccountNumber);
        if (sourceAccount == null) {
            throw new NotFoundException("Source account not found");
        }

        Account targetAccount = accountRepository.findByAccountNumber(targetAccountNumber);
        if (targetAccount == null) {
            throw new NotFoundException("Target account not found");
        }

        if (!passwordEncoder.matches(pin, sourceAccount.getPin())) {
            throw new UnauthorizedException("Invalid PIN");
        }

        double sourceBalance = sourceAccount.getBalance();
        if (sourceBalance < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        double newSourceBalance = sourceBalance - amount;
        sourceAccount.setBalance(newSourceBalance);
        accountRepository.save(sourceAccount);

        double targetBalance = targetAccount.getBalance();
        double newTargetBalance = targetBalance + amount;
        targetAccount.setBalance(newTargetBalance);
        accountRepository.save(targetAccount);

        Transaction transaction = new Transaction();
        transaction.set(amount);
        transaction.setTransactionType(TransactionType.CASH_TRANSFER);
        transaction.setTransaction_date(new Date());
        transaction.setSourceAccount(sourceAccount);
        transaction.setTargetAccount(targetAccount);
        transactionRepository.save(transaction);
    }
}
