package ttcs.btl.service.email;

import io.jsonwebtoken.Claims;
import jakarta.mail.MessagingException;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ttcs.btl.repository.clients.IClientRepo;
import ttcs.btl.repository.error.ArgumentException;
import ttcs.btl.service.auth.TokenProvider;


@Service
@RequiredArgsConstructor
public class EmailService {
    final private String senderEmail = "ngocbeo3387@gmail.com";
    final private JavaMailSender mailSender;
    private final TokenProvider tokenProvider;
    private final IClientRepo iClientRepo;
    private final PasswordEncoder passwordEncoder;

    public String sendEmail(String to) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject("QUÊN MẬT KHẨU");

        String encodeEmail = tokenProvider.createJwtToken(to);

        String htmlContent = "<h1>BẠN ĐÃ YÊU CẦU LẤY LẠI MẬT KHẨU</h1>" +
                "<p>BẠN ĐÃ YÊU CẦU LẤY LẠI MẬT KHẨU</p>"+
                "<a href=\"http://localhost:2002/reset-password/"+encodeEmail+"\">Nhấn vào đường link để lấy lại " +
                "mật khẩu</a>";
        message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);
        System.out.println("Mail sent successfully");
        return "success";
    }

    public String resetPassword(String e){
        try{
            Claims claims = tokenProvider.decodeJwt(e);
            final String email = claims.getSubject();
            final var isExistEmail = iClientRepo.findByEmail(email);
            if(isExistEmail == null){
                throw new ArgumentException("Cảnh báo!!");
            }else{
                isExistEmail.setPassword(passwordEncoder.encode("12345678"));
                iClientRepo.save(isExistEmail);
            }
        }catch (ArgumentException argumentException){
            throw new ArgumentException("Cảnh báo!!");
        }

        return "success";
    }
}
