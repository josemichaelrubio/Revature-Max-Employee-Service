package com.revaturemax.controllers;

import com.revaturemax.services.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailNotificationService emailNotificationService;

    @GetMapping
    public void sendTestEmail(){
        emailNotificationService.sendSimpleEmail("duncan.asplundh@revature.net", "Hello!", "Tis functional");
    }
}
