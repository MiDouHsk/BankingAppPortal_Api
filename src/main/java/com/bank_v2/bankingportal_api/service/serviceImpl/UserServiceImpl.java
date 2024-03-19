package com.bank_v2.bankingportal_api.service.serviceImpl;

import com.bank_v2.bankingportal_api.dto.UserDto;
import com.bank_v2.bankingportal_api.entity.*;
import com.bank_v2.bankingportal_api.exception.UserValidation;
import com.bank_v2.bankingportal_api.repository.AccountRepository;
import com.bank_v2.bankingportal_api.repository.UserRepository;
import com.bank_v2.bankingportal_api.service.AccountService;
import com.bank_v2.bankingportal_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;

    @Override
    public User registerUser(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
        user.getAccount().setAccountNumber(generateUniqueAccountNumber());
        user.getAccount().setUser(user);
        Role role = user.getRole();
        role.setRoleType(RoleType.ROLE_USER);
        return userRepository.save(user);
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            // Generate a UUID as the account number
            accountNumber = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
        } while (accountRepository.findByAccountNumber(accountNumber) != null);

        return accountNumber;
    }
    @Override
    public User getUserByAccountNumber(Long id, String account_no) {
        return userRepository.findByAccountAccountNumber(account_no);
    }

    @Override
    public void savedUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserDto user) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserValidation("User not found with id: " + id));

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            existingUser.setEmail(user.getEmail());
        } else {
            throw new UserValidation("Email cannot be empty");
        }
        if (user.getName() != null && !user.getName().isEmpty()) {
            existingUser.setName(user.getName());
        } else {
            throw new UserValidation("Name cannot be empty");
        }
        if (user.getPhone_number() != null && !user.getPhone_number().isEmpty()) {
            existingUser.setPhone_number(user.getPhone_number());
        } else {
            throw new UserValidation("Phone number cannot be empty");
        }
        if (user.getAddress() != null) {
            existingUser.setAddress(user.getAddress());
        }
        return userRepository.save(existingUser);
    }
}
