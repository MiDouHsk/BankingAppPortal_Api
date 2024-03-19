package com.bank_v2.bankingportal_api.service.serviceImpl;

import com.bank_v2.bankingportal_api.dto.TransactionDto;
import com.bank_v2.bankingportal_api.entity.Transaction;
import com.bank_v2.bankingportal_api.mapper.TransactionMapper;
import com.bank_v2.bankingportal_api.repository.TransactionRepository;
import com.bank_v2.bankingportal_api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public List<TransactionDto> getAllTransactionsByAccountNumber(String accountNumber) {
        List<Transaction> transactions = transactionRepository.findBySourceAccount_AccountNumberOrTargetAccount_AccountNumber(accountNumber, accountNumber);

        List<TransactionDto> transactionDtos = transactions.stream()
                .map(transactionMapper::toTransactionDto)
                .sorted((t1, t2) -> t2.getTransaction_date().compareTo(t1.getTransaction_date()))
                .collect(Collectors.toList());

        return transactionDtos;
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(transactionMapper::toTransactionDto)
                .collect(Collectors.toList());
    }
}