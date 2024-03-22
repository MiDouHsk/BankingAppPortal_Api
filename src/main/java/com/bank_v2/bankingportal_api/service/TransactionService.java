package com.bank_v2.bankingportal_api.service;

import java.util.List;
import com.bank_v2.bankingportal_api.dto.TransactionDto;
import com.bank_v2.bankingportal_api.entity.Transaction;

public interface TransactionService {
    List<TransactionDto> getAllTransactionsByAccountNumber(String accountNumber);

    List<TransactionDto> getAllTransactions();
}
