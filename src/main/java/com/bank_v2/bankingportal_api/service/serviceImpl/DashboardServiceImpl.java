package com.bank_v2.bankingportal_api.service.serviceImpl;

import com.bank_v2.bankingportal_api.dto.AccountDto;
import com.bank_v2.bankingportal_api.dto.UserDto;
import com.bank_v2.bankingportal_api.entity.Account;
import com.bank_v2.bankingportal_api.entity.User;
import com.bank_v2.bankingportal_api.repository.AccountRepository;
import com.bank_v2.bankingportal_api.repository.UserRepository;
import com.bank_v2.bankingportal_api.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public UserDto getUserDetails(String accountNumber) throws ClassNotFoundException {
        User user = userRepository.findByAccountAccountNumber(accountNumber);
        if (user == null) {
            throw new ClassNotFoundException("User not found for the provided account number.");
        }
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAddress(user.getAddress());
        userDto.setPhone_number(user.getPhone_number());
        userDto.setAccountNumber(user.getAccount().getAccountNumber());
        return userDto;
    }
    @Override
    public AccountDto getAccountDetails(String accountNumber) throws ClassNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null)
        {
            throw new ClassNotFoundException("Account not found for the provided account number.");
        }
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountNumber(account.getUser().getName());
        accountDto.setAccountType(account.getAccount_type());
        accountDto.setBalance(account.getBalance());
        accountDto.setBranch(account.getBranch());
        accountDto.setIFSCCode(account.getIFSC_code());
        return accountDto;
    }
}
