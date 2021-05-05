package com.revaturemax.services;



import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.validation.constraints.Email;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmailNotificationServiceTest {



    @Test
    public void emailNotificationServiceTest(){
        EmailService emailNotificationService = mock(EmailService.class);
        emailNotificationService.sendEmail("test@email.com", "test subject", "test Text");
        verify(emailNotificationService, times(1)).sendEmail("test@email.com", "test subject", "test Text");
    }
}
