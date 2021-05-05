package com.revaturemax.controllers;

import com.revaturemax.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @GetMapping
    public void sendTestEmail(){
        emailService.sendEmail("duncan.asplundh@revature.net", "Hello!", "Tis functional");
    }
}
