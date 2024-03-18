package com.bank_v2.bankingportal_api.mapper;

import com.bank_v2.bankingportal_api.dto.TransactionDto;
import com.bank_v2.bankingportal_api.entity.Transaction;
import org.modelmapper.ModelMapper;

public class TransactionMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static TransactionDto toTransactionDto(Transaction transaction) {
        return modelMapper.map(transaction, TransactionDto.class);
    }

    public static Transaction toTransaction(TransactionDto transactionDto) {
        return modelMapper.map(transactionDto, Transaction.class);
    }
}
