package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.EmailService;

@RestController
public class NotificationController {

    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send-email")
    public String sendEmail() {
        String to = "727722euit116@skcet.ac.in";
        String subject = "Welcome Sir,Intern at Jobizz";
        String message = "Hi Naveen,\\n" + //
                "\\n" + //
                "Please check out this link: https://www.example.com\\n" + //
                "\\n" + //
                "Best regards,\\n" + //
                "Jobizz community";
        emailService.sendEmail(to, subject, message);
        return "Email sent successfully!";
    }
}
