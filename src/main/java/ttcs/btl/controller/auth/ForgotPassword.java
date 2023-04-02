package ttcs.btl.controller.auth;


import io.jsonwebtoken.Claims;
import jakarta.mail.MessagingException;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.service.auth.TokenProvider;
import ttcs.btl.service.email.EmailService;

@RestController
@RequestMapping(value = "/api/v1/", produces = "application/json")
@RequiredArgsConstructor
public class ForgotPassword {

    private final EmailService emailService;

    @GetMapping("forgot-password")
    public String sendMailForgotPassword(@RequestParam String email) throws MessagingException {
        return emailService.sendEmail(email);
    }

    @GetMapping("reset-password")
    public String resetPassword(@RequestParam String e) {
        return emailService.resetPassword(e);
    }

}
