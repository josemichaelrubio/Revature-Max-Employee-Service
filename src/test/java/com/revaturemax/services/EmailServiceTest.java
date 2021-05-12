package com.revaturemax.services;



import com.revaturemax.models.Employee;
import com.revaturemax.models.Role;
import com.revaturemax.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {


    @InjectMocks
    EmailService emailService;

    @MockBean
    EmployeeRepository employeeRepository;

    @MockBean
    EmployeeService employeeService;

    @Test
    public void emailNotificationServiceTest() throws MessagingException {
//        MimeMessage message = mock(MimeMessage.class);
//        Session session = mock(Session.class);
//
//        mockStatic(Transport.class);
//        doNothing().when(Transport.class);
//        Transport.send(any());
//
//        emailService.sendEmail("test@email.com", "test subject", "test Text");
//
//        PowerMockito.verifyStatic() is the method I want to use. need powermockito tho
//        verify(emailService, times(1)).sendEmail("test@email.com", "test subject", "test Text");
    }

    @Test
    public void verifyEmailTest(){
        Employee employeeParam = new Employee(1L, Role.GUEST, "name", "email@web.com", "123-456-7890", "address", "pictureUrl");
        Employee employeeUpdated = new Employee(1L, Role.ASSOCIATE, "name", "email@web.com", "123-456-7890", "address", "pictureUrl");
        when(employeeRepository.findById(1L))
                .thenReturn(java.util.Optional.of(employeeParam));

        emailService.verifyEmail(1L);

        verify(employeeService).updateEmployee(1L, employeeUpdated);
    }

    //todo in progess, method subject to change
    @Test
    public void batchInvite_guestAndAssociate(){
        EmployeeRepository repository = mock(EmployeeRepository.class);
        EmployeeService service = mock(EmployeeService.class);
        EmailService spy = spy(new EmailService(service, repository));
        Employee trainer = new Employee(1L, Role.INSTRUCTOR, "trainer name", "trainer@web.com", "123-456-7890", "address", "pictureUrl");

        Employee emp1 = new Employee(2L, Role.ASSOCIATE, "empl 2", "empl2@web.com", "123-456-7890", "address", "pictureUrl");
        Employee emp2 = new Employee(3L, Role.GUEST, "empl 3", "empl3@web.com", "123-456-7890", "address", "pictureUrl");
        List<String> emails = Arrays.asList(emp1.getEmail(), emp2.getEmail());

        when(repository.findById(trainer.getId())).thenReturn(Optional.of(trainer));
        when(service.getEmployeeByEmail(emp1.getEmail())).thenReturn(emp1);
        when(service.getEmployeeByEmail(emp2.getEmail())).thenReturn(emp2);

        doNothing().when(spy).sendVerify(anyString(), anyLong());
        doNothing().when(spy).sendEmail(anyString(), anyString(), anyString());

        spy.batchInvite(emails, "name", "description", "location", trainer.getId());

        verify(spy).sendVerify(emp2.getEmail(), emp2.getId());
        verify(spy).sendEmail(emp1.getEmail(), "You've been added to batch", "trainer name has added you to the name batch\nDescription: description\nLocation: location");
    }
}
