/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author PC
 */
public class MailService {
    public static void sendMail(String email, String otp) {
        final String username = "anhpham161223@gmail.com";
        final String password = "yncxrpkafqdkdksf";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

      try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("VERIFY YOUR OTP FROM VIVATIMESHARE"); // Email subject

            // Email content with enhanced design
            String htmlBody = "<html><head><style>" +
                              "body { font-family: Arial, sans-serif; }" +
                              ".header { color: #007bff; }" +
                              ".otp { color: #28a745; }" +
                              ".footer { margin-top: 20px; text-align: center; color: #6c757d; }" +
                              ".content { color: #343a40; }" +
                              "</style></head>" +
                              "<body>" +
                              "<h1 class='header'>VERIFY YOUR OTP FROM VIVATIMESHARE</h1>" +
                              "<p class='content'>Your OTP is: <strong class='otp'>" + otp + "</strong></p>" +
                              "<p class='content'>Please enter this code on the website to complete the verification process.</p>" +
                              "<p class='footer'>Thank you from VivaTimeshare.</p>" +
                              "</body></html>";

            message.setContent(htmlBody, "text/html");

            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}