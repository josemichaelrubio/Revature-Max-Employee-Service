package com.revaturemax.services;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {



    @Test
    public void emailNotificationServiceTest(){
        EmailService emailService = mock(EmailService.class);
        emailService.sendEmail("test@email.com", "test subject", "test Text");
        verify(emailService, times(1)).sendEmail("test@email.com", "test subject", "test Text");
    }

    @Test
    public void verifyEmailTest(){
        EmailService emailService = mock(EmailService.class);
        emailService.verifyEmail(1L);
        verify(emailService, times(1)).verifyEmail(1L);
    }

    //todo in progess, method subject to change
//    @Test
//    public void batchInvite_ifGuestVerfied(){
//        EmailService emailService = mock(EmailService.class);
//        EmployeeService employeeService = mock(EmployeeService.class);
//        Employee employee = new Employee();
//        employee.setId(1L);
//        employee.setName("tom");
//        employee.setRole(Role.ASSOCIATE);
//        employee.setEmail("test@test.com");
//        ResponseEntity<Employee> responseEntity = new ResponseEntity<>(employee, HttpStatus.OK);
//        emailService.batchInvite(1l, 1l);
//        when(employeeService.getEmployee(1l)).thenReturn(responseEntity);
//        verify(emailService, times(1)).verifyEmail(employee.getId());
//        verify(emailService, times(1)).sendEmail(employee.getEmail(), "You've been added to a batch!", "Employee " + employee.getName() + ", has been added to batch " + 1l);
//
//    }
}
