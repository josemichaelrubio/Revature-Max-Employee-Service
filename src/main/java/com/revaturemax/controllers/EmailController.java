package com.revaturemax.controllers;

import com.revaturemax.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@CrossOrigin
@RequestMapping("/verify")
public class EmailController {

    @Autowired
    EmailService emailService;

    @GetMapping
    public void inviteBatchEmail(@RequestParam String email, @RequestParam Long batchId){
        System.out.println("endpoint hit");
        emailService.batchInvite(email, batchId);
    }

    @GetMapping(path = "/{employeeId}")
    public void verifyEmail(@PathVariable Long employeeId) {emailService.verifyEmail(employeeId);}
}
