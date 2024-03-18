package com.bank_v2.bankingportal_api.service;

import java.util.List;
import com.bank_v2.bankingportal_api.dto.TransactionDto;

public interface TransactionService {
    List<TransactionDto> getAllTransactionsByAccountNumber(String accountNumber);
}
