package com.bank_v2.bankingportal_api.util;

//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.webjars.NotFoundException;
//
//public class LoggedinUser {
//
//    public static String getAccountNumber() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if( authentication != null) {
//            Object principal = authentication.getPrincipal();
//
//            if(principal instanceof User) {
//                User user = (User) principal;
//                return user.getUsername();
//            }
//        }
//        throw new NotFoundException("Account number not found in Security Context.");
//    }
//}
