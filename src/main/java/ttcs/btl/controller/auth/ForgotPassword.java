package ttcs.btl.controller.auth;


import io.jsonwebtoken.Claims;
import jakarta.mail.MessagingException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.dto.auth.ResetPasswordRequest;
import ttcs.btl.model.client.EWaitingR;
import ttcs.btl.service.auth.TokenProvider;
import ttcs.btl.service.email.EmailService;

import java.util.List;

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

    @GetMapping("get-all-ewaitingr")
    public List<EWaitingR> getAllEWaitingR() {
        return emailService.getAllEWaitingR();
    }

    @PostMapping("access-reset")
    public Boolean accessResetPassword(@RequestBody @Valid ResetPasswordRequest resetPasswordRequest){
        return emailService.accessResetPassword(resetPasswordRequest);
    }

    @PostMapping("delete-forgotpass-ids")
    public Boolean deleteFeedbackIds(@RequestBody List<Long> ids){
        try{
            emailService.deleteUserEmailWithIds(ids);
            return true;
        }catch (Exception e){
            return false;
        }

    }

}
