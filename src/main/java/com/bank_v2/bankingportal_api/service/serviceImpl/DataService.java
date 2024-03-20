package com.bank_v2.bankingportal_api.service.serviceImpl;


import com.bank_v2.bankingportal_api.entity.Role;
import com.bank_v2.bankingportal_api.entity.Role.RoleType;
//import com.bank_v2.bankingportal_api.entity.RoleType;
import com.bank_v2.bankingportal_api.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DataService {
    private RoleRepository roleRepository;
    @PostConstruct
    public void initializeData() {
        initializeRoles();
    }
    private void initializeRoles() {
        if (roleRepository.findByRoleName(RoleType.ROLE_ADMIN) == null) {
            Role adminRole = new Role();
            adminRole.setRoleName(RoleType.ROLE_ADMIN);
            roleRepository.save(adminRole);
        }
        if (roleRepository.findByRoleName(RoleType.ROLE_USER) == null) {
            Role userRole = new Role();
            userRole.setRoleName(RoleType.ROLE_USER);
            roleRepository.save(userRole);
        }
    }
}
