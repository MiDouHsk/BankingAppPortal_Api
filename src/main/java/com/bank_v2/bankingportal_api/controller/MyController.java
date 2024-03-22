package com.bank_v2.bankingportal_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RestController
public class MyController {
    @GetMapping("/delete")
    public String deletePage() {
        return "redirect:/deletePage.html";
    }
}
