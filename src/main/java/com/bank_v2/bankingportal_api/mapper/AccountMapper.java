package com.bank_v2.bankingportal_api.mapper;

import com.bank_v2.bankingportal_api.dto.AccountDto;
import com.bank_v2.bankingportal_api.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    @Autowired
    private ModelMapper modelMapper;
    public AccountDto toAccountDto(Account account) {
        return modelMapper.map(account, AccountDto.class);
    }
    public Account toAccountEntity(AccountDto accountDto) {
        return modelMapper.map(accountDto, Account.class);
    }

}
