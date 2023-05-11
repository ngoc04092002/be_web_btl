package ttcs.btl.service.email;

import io.jsonwebtoken.Claims;
import jakarta.mail.MessagingException;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.auth.ResetPasswordRequest;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.model.client.EWaitingR;
import ttcs.btl.repository.clients.IClientRepo;
import ttcs.btl.repository.clients.IEWRRepo;
import ttcs.btl.repository.error.ArgumentException;
import ttcs.btl.repository.error.EditProfileException;
import ttcs.btl.service.auth.TokenProvider;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class EmailService {
    final private String senderEmail = "ngocbeo3387@gmail.com";
    final private JavaMailSender mailSender;
    private final TokenProvider tokenProvider;
    private final IClientRepo iClientRepo;
    private final IEWRRepo iewrRepo;
    private final PasswordEncoder passwordEncoder;

    public String sendEmail(String to) throws MessagingException {
        final ClientEntity clientEntity = iClientRepo.findByEmail(to);
        if(clientEntity==null){
            throw new EditProfileException("");
        }
        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject("QUÊN MẬT KHẨU");

        String encodeEmail = tokenProvider.createJwtToken(to, "user");

        String htmlContent = "<h1>BẠN ĐÃ YÊU CẦU LẤY LẠI MẬT KHẨU</h1>" +
                "<p>KHÔNG ĐƯỢC CUNG CÂP ĐƯỜNG LINK BÊN DƯỚI TỚI NGƯỜI KHÁC!</p>"+
                "<a href=\"http://localhost:2002/reset-password/"+encodeEmail+"\">Nhấn vào đường link để lấy lại " +
                "mật khẩu</a>";
        message.setContent(htmlContent, "text/html; charset=utf-8");
        Optional<EWaitingR> isEmailExist = Optional.ofNullable(iewrRepo.findByEmail(to));
        if(!isEmailExist.isPresent()){
            EWaitingR eWaitingR = EWaitingR.builder()
                    .email(to)
                    .build();
            iewrRepo.save(eWaitingR);
        }
        mailSender.send(message);
        System.out.println("Mail sent successfully");
        return "success";
    }

    public String resetPassword(String e){
        try{
            Claims claims = tokenProvider.decodeJwt(e);
            final String email = claims.getSubject();
            final var isExistEmailCLient = iClientRepo.findByEmail(email);
            Optional<EWaitingR> isEmailExistEWR = Optional.ofNullable(iewrRepo.findByEmail(email));

            if(isExistEmailCLient == null ||isEmailExistEWR.isEmpty()){
                throw new ArgumentException("Cảnh báo!!");
            }

        }catch (ArgumentException argumentException){
            throw new ArgumentException("Cảnh báo!!");
        }

        return "success";
    }

    public Boolean accessResetPassword(ResetPasswordRequest resetPasswordRequest){
        try {
            Claims claims = tokenProvider.decodeJwt(resetPasswordRequest.getEmail());
            final String decodeEmail = claims.getSubject();
            final var isExistEmailCLient = iClientRepo.findByEmail(decodeEmail);

            isExistEmailCLient.setPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
            iewrRepo.deleteByEmail(decodeEmail);
            iClientRepo.save(isExistEmailCLient);
            return true;
        }catch (Exception ex){
            System.out.println("EmailService=>>>>"+ex.getMessage());
            return false;
        }
    }

    public List<EWaitingR> getAllEWaitingR(){
        return iewrRepo.findAll();
    }


    public void deleteUserEmailWithIds(List<Long> ids) {
        iewrRepo.deleteUserEmailWithIds(ids);
    }

}
