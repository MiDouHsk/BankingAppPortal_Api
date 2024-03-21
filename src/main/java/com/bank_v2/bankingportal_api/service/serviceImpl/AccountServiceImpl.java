package com.bank_v2.bankingportal_api.service.serviceImpl;

import com.bank_v2.bankingportal_api.entity.*;
import com.bank_v2.bankingportal_api.exception.InsufficientBalanceException;
import com.bank_v2.bankingportal_api.exception.NotFoundException;
import com.bank_v2.bankingportal_api.repository.AccountRepository;
import com.bank_v2.bankingportal_api.repository.TransactionRepository;
import com.bank_v2.bankingportal_api.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.bank_v2.bankingportal_api.entity.Account;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

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
    public void fundTransfer(String sourceAccountNumber, String targetAccountNumber, String pin, double amount) {
        Account sourceAccount = accountRepository.findByAccountNumber(sourceAccountNumber);
        if (sourceAccount == null) {
            throw new NotFoundException("Source account not found");
        }

        Account targetAccount = accountRepository.findByAccountNumber(targetAccountNumber);
        if (targetAccount == null) {
            throw new NotFoundException("Target account not found");
        }

        double sourceBalance = sourceAccount.getBalance();
        if (sourceBalance < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        double newSourceBalance = sourceAccount.getBalance() - amount;
        sourceAccount.setBalance(newSourceBalance);
        accountRepository.save(sourceAccount);

        double newTargetBalance = targetAccount.getBalance() + amount;
        targetAccount.setBalance(newTargetBalance);
        accountRepository.save(targetAccount);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.CASH_TRANSFER);
        transaction.setTransaction_date(new Date());
        transaction.setSourceAccount(sourceAccount);
        transaction.setTargetAccount(targetAccount);
        transactionRepository.save(transaction);
    }
    @Override
    public void deleteAccountsByUserId(Long id) {
        List<Account> accounts = accountRepository.deleteAccountsByUserId(id);
        accountRepository.deleteAll(accounts);
    }

    @Override
    public void deleteAccountWithTransactions(Long accountId) {
        try{
            transactionRepository.deleteBySourceAccount_Id(accountId);
            transactionRepository.deleteByTargetAccount_Id(accountId);

            deleteAccountsByUserId(accountId);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
