package com.revaturemax.controllers;

import com.revaturemax.models.Employee;
import com.revaturemax.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/verify")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void inviteBatchEmail(@RequestBody List<String> emails) {
        System.out.println("endpoint hit");
        emailService.batchInvite(emails);
    }

    @GetMapping(path = "/{employeeId}")
    public RedirectView verifyEmail(@PathVariable Long employeeId) {
        emailService.verifyEmail(employeeId);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:4200/login"); // redirecting to login page
        return redirectView;
    }
}

