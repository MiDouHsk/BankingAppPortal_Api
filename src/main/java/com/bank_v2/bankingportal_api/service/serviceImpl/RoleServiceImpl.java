//package com.bank_v2.bankingportal_api.service.serviceImpl;
//
//import com.bank_v2.bankingportal_api.entity.Role;
//import com.bank_v2.bankingportal_api.repository.RoleRepository;
//import com.bank_v2.bankingportal_api.service.RoleService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@AllArgsConstructor
//@Service
//public class RoleServiceImpl implements RoleService {
//
//    private final RoleRepository roleRepository;
//
//    @Override
//    public Role saveRole(Role role) {
//        return roleRepository.save(role);
//    }
//
//    @Override
//    public Role getRoleById(Long id) {
//        return roleRepository.findById(id).orElse(null);
//    }
//}
