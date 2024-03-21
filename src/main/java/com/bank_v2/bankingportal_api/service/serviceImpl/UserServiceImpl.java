package com.bank_v2.bankingportal_api.service.serviceImpl;


import com.bank_v2.bankingportal_api.dto.UserDto;
import com.bank_v2.bankingportal_api.entity.*;
import com.bank_v2.bankingportal_api.exception.UserValidation;
import com.bank_v2.bankingportal_api.repository.AccountRepository;
import com.bank_v2.bankingportal_api.repository.RoleRepository;
import com.bank_v2.bankingportal_api.repository.UserRepository;
import com.bank_v2.bankingportal_api.service.AccountService;
import com.bank_v2.bankingportal_api.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private AccountService accountService;


    @Override
    public User registerUser(UserDto dto) {
        try{
            User user = modelMapper.map(dto, User.class);

            String hashedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);

            user.getAccount().setAccountNumber(generateUniqueAccountNumber());
            user.getAccount().setUser(user);

            LocalDateTime now = LocalDateTime.now();
            user.setCreateAt(now);
//            user.setUpdateAt(now);

            Role userRole = roleRepository.findByRoleName(Role.RoleType.ROLE_USER);
            user.setRole(userRole);

            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();

            throw new RuntimeException("Lỗi khi đăng ký người dùng: " + e.getMessage());
        }

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
        try{
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
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
                existingUser.setPassword(hashedPassword);
            }

            existingUser.setUpdateAt(LocalDateTime.now());

            return userRepository.save(existingUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi cập nhâp người dùng: " + e.getMessage());
        }
    }
    @Override
    public boolean login(String account, String password) {
        try {
            User user = userRepository.findByAccountAccountNumber(account);
            if (user != null) {

                String hashedPassword = user.getPassword();
                boolean passwordMatch = BCrypt.checkpw(password, hashedPassword);
                return passwordMatch;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void DeleteUser(Long UserId) {
        userRepository.deleteById(UserId);

        accountService.deleteAccountsByUserId(UserId);
    }
}
