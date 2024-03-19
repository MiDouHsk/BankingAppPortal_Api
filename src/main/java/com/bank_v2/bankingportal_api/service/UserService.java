package com.bank_v2.bankingportal_api.service;

import com.bank_v2.bankingportal_api.dto.UserDto;
import com.bank_v2.bankingportal_api.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {

    public User registerUser(UserDto user);
    User getUserByAccountNumber(Long id, String account_no);
    public void savedUser(User user);
    User updateUser(Long id, UserDto user);
}
