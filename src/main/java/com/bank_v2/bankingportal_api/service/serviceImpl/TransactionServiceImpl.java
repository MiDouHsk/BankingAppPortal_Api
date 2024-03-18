package com.bank_v2.bankingportal_api.service.serviceImpl;

import com.bank_v2.bankingportal_api.dto.TransactionDto;
import com.bank_v2.bankingportal_api.mapper.TransactionMapper;
import com.bank_v2.bankingportal_api.repository.TransactionRepository;
import com.bank_v2.bankingportal_api.service.TransactionService;
import jakarta.transaction.Transaction;
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

        List<TransactionDto> transactionDTOs = transactions.stream()
                .map(transactionMapper::toTransactionDto)
                .sorted(Comparator.comparing(TransactionDto::getTransaction_date).reversed())
                .collect(Collectors.toList());

        return transactionDTOs;


}