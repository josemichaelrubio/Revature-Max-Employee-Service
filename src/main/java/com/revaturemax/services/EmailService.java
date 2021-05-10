package com.revaturemax.services;
import com.revaturemax.models.Employee;
import com.revaturemax.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        ResponseEntity<Employee> response = employeeService.getEmployee(employeeId);
        Employee employee = response.getBody();
        if(employee.getRole().equals(Role.GUEST)){
            employee.setRole(Role.ASSOCIATE);
            employeeService.updateEmployee(employee.getId(), employee);
        }
    }

    public void batchInvite(List<String> emails, String name, String description, String location, Long trainerId) {
        ResponseEntity<Employee> trainer = employeeService.getEmployee(trainerId);
        String trainerName = trainer.getBody().getName();
        description = description.replaceAll("%20", " ");
        String output = String.format("%s has added you to the %s batch\nDescription: %s\nLocation: %s", trainerName, name, description, location);
        for(String email: emails){
            Employee employee = employeeService.getEmployeeByEmail(email);
            if (!employee.getRole().equals(Role.GUEST)) {
                sendEmail(email, "You've been added to batch", output);
            } else {
                sendVerify(email, employee.getId());
            }
        }
    }

    public void sendVerify(String email, long id) {
        // Todo Don't forget to change endpoint to VM address
        String link = "http://localhost:8082/verify/" + id;
        sendEmail(email, "Verification link", link);
    }

}
