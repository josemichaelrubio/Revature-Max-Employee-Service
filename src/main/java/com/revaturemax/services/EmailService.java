package com.revaturemax.services;
import com.revaturemax.models.Employee;
import com.revaturemax.models.Role;
import com.revaturemax.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;

    public void sendEmail(String to, String subject, String text) {

        // Recipient's email ID needs to be mentioned.


        // Sender's email ID needs to be mentioned
        String from = "revaturemaxcompany@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("revaturemaxcompany@gmail.com", "emailcrew!3");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(text);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

    public void verifyEmail(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if(employee != null && employee.getRole().equals(Role.GUEST)){
            employee.setRole(Role.ASSOCIATE);
            employeeService.updateEmployee(employee.getId(), employee);
        }
    }

    public void batchInvite(List<String> emails) {
        for(String email: emails){
            Employee employee = employeeService.getEmployeeByEmail(email);
            if (!employee.getRole().equals(Role.GUEST)) {
                sendEmail(employee.getEmail(), "You've been added to a batch!", "Employee " + employee.getName() + ", has been added to a batch!");
            } else {
                // Todo be sure to update this link to our VM address
                String link = "http://localhost:8082/verify/" + employee.getId();
                sendEmail(employee.getEmail(), "Verification Link", link);
            }
        }
    }
}
