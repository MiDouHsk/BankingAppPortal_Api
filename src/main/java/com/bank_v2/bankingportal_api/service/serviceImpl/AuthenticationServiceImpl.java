package com.bank_v2.bankingportal_api.service.serviceImpl;

import com.bank_v2.bankingportal_api.entity.User;
import com.bank_v2.bankingportal_api.exception.UserValidation;
import com.bank_v2.bankingportal_api.repository.UserRepository;
import com.bank_v2.bankingportal_api.service.AuthenticationService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean authenticate(String account, String password) {
        User user = userRepository.findByAccountAccountNumber(account);
        if (user == null) {
            throw new UserValidation("Invalid username or password");
        }
        return BCrypt.checkpw(password, user.getPassword());
    }
}
