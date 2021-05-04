package com.revaturemax.services;



import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmailNotificationServiceTest {

    @Autowired
    EmailNotificationService emailNotificationService;

    @Test
    public void emailNotificationServiceTest(){
        doNothing().when(emailNotificationService).sendSimpleEmail("test@email.com", "test subject", "test Text");
        verify(emailNotificationService, times(1)).sendSimpleEmail("test@email.com", "test subject", "test Text");
    }
}
