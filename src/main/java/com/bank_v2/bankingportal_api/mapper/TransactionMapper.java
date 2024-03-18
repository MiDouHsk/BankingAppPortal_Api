package com.bank_v2.bankingportal_api.mapper;

import com.bank_v2.bankingportal_api.dto.TransactionDto;
import com.bank_v2.bankingportal_api.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    @Autowired
    private ModelMapper modelMapper;

    public TransactionDto toTransactionDto(Transaction transaction) {
        return modelMapper.map(transaction, TransactionDto.class);
    }

    public Transaction toTransaction(TransactionDto transactionDto) {
        return modelMapper.map(transactionDto, Transaction.class);
    }
}
