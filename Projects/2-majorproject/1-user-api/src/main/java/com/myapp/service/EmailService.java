package com.myapp.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    // 1) Send plain text email
    public void sendSimpleEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        sender.send(message);
        System.out.println("Simple email sent to: " + toEmail);
    }

    // 2) Send HTML email
    public void sendHtmlEmail(String toEmail, String subject, String htmlBody) throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(htmlBody, true); // true = isHtml
        sender.send(mimeMessage);
        System.out.println("HTML email sent to: " + toEmail);
    }

    // 3) Send email with attachment
    public void sendEmailWithAttachment(String toEmail, String subject, String body, String filePath) throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body, false);
        FileSystemResource file = new FileSystemResource(new File(filePath));
        helper.addAttachment(file.getFilename(), file);
        sender.send(mimeMessage);
        System.out.println("Email with attachment sent to: " + toEmail);
    }

    // 4) Send password reset email (common use case)
    public void sendPasswordResetEmail(String toEmail, String resetLink) throws MessagingException {
        String subject = "Password Reset Request";
        String htmlBody = "<h3>Password Reset</h3>"
                + "<p>Click the link below to reset your password:</p>"
                + "<a href='" + resetLink + "'>Reset Password</a>"
                + "<p>This link is valid for 30 minutes.</p>"
                + "<p>If you did not request this, please ignore this email.</p>";
        sendHtmlEmail(toEmail, subject, htmlBody);
    }

    // 5) Send account activation email
    public void sendAccountActivationEmail(String toEmail, String activationLink) throws MessagingException {
        String subject = "Activate Your Account";
        String htmlBody = "<h3>Welcome!</h3>"
                + "<p>Click the link below to activate your account:</p>"
                + "<a href='" + activationLink + "'>Activate Account</a>"
                + "<p>If you did not register, please ignore this email.</p>";
        sendHtmlEmail(toEmail, subject, htmlBody);
    }

}
