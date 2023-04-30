package ttcs.btl.controller.auth;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.dto.auth.AuthRequest;
import ttcs.btl.dto.auth.AuthRequestSocial;
import ttcs.btl.dto.auth.AuthResponse;
import ttcs.btl.dto.auth.UserResponse;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.repository.error.ArgumentException;
import ttcs.btl.repository.error.ResourceFoundException;
import ttcs.btl.repository.error.ResourceNotFoundException;
import ttcs.btl.repository.error.ValidateException;
import ttcs.btl.service.auth.IAuthService;
import ttcs.btl.service.auth.TokenProvider;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/", produces = "application/json")
public class AuthController {
    private final String REGEX_EMAIL = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]+\\b";
    private final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{8,}$";
    private final String defaultPasswordSignInWithSocial = "12345678A@";
    private final PasswordEncoder passwordEncoder;

    private final IAuthService iAuthService;
    private final TokenProvider tokenProvider;

    @Value("${app.auth.tokenCookieName}")
    public String tokenCookieName;


    @GetMapping("get-user-info")
    public AuthResponse getUserInfo(@RequestParam String access) {
        final var isAccess = tokenProvider.validateToken(access);
        if(!isAccess){
            throw new ArgumentException("Hết phiên đăng nhập");
        }
        Claims claims = tokenProvider.decodeJwt(access);
        String email = claims.getSubject();
        final var user = iAuthService.fetchUser(email);

        if (user == null) {
            throw new ResourceNotFoundException("Email "+email);
        }
        return new AuthResponse(user, access);
    }

    @PostMapping("sign-up")
    public String createUser(@RequestBody ClientEntity clientEntity) {
        try {
            Pattern patternEmail = Pattern.compile(REGEX_EMAIL, Pattern.CASE_INSENSITIVE);
            Pattern patternPassword = Pattern.compile(REGEX_PASSWORD, Pattern.CASE_INSENSITIVE);
            Matcher matcherEmail = patternEmail.matcher(clientEntity.getEmail());
            Matcher matcherPassword = patternPassword.matcher(clientEntity.getPassword());
            if(!matcherEmail.matches()){
                throw new ValidateException("Email không hợp lệ!");
            }
            if(!matcherPassword.matches()){
                throw new ValidateException("Password cần có ký tự đặc biệt, chữ,số!");
            }
            String encodedPassword = passwordEncoder.encode(clientEntity.getPassword());
            UserResponse userResponse = new UserResponse(clientEntity, encodedPassword);
            String email = clientEntity.getEmail();

            final var user = iAuthService.fetchUser(email);
            if (user == null) {
                iAuthService.saveUser(userResponse);
            } else {
                throw new ResourceFoundException("email");
            }
            return "Bạn đã đăng ký thành công";
        } catch (ArgumentException ae) {
            throw new ArgumentException(ae.getMessage());
        }
    }

    @PostMapping("sign-in")
    public AuthResponse authResponse(@RequestBody @Valid AuthRequest authRequest, HttpServletResponse response) {
        final String email = authRequest.getEmail();
        final String password = authRequest.getPassword();
        final var user = iAuthService.fetchUser(email);

        if (user == null) {
            throw new ResourceNotFoundException("Email "+email);
        }

        boolean isMatcher = passwordEncoder.matches(password, user.getPassword());
        if (!isMatcher) {
            throw new ResourceNotFoundException("Email "+email);
        } else {
            final var token = tokenProvider.createJwtToken(email, "user");
            addTokenCookie(response, tokenCookieName, token);
            return new AuthResponse(user, token);
        }
    }

    @PostMapping("sign-in-social")
    public AuthResponse authResponseWithSocial(@RequestBody @Valid AuthRequestSocial authRequestSocial,
            HttpServletResponse response) {
        final String email = authRequestSocial.getEmail();
        final var user = iAuthService.fetchUser(email);
        final var token = tokenProvider.createJwtToken(email, "user");
        addTokenCookie(response, tokenCookieName, token);
        if (user == null) {
            String encodedPassword = passwordEncoder.encode(defaultPasswordSignInWithSocial);
            ClientEntity clientEntity = new ClientEntity(authRequestSocial,encodedPassword);
            UserResponse userResponse = new UserResponse(clientEntity, encodedPassword);
            iAuthService.saveUser(userResponse);
            return new AuthResponse(clientEntity, token);
        }

        return new AuthResponse(user, token);
    }

    @GetMapping("refresh-cookie")
    public String refreshCookie(@RequestParam String token) {
        final var isAccess = tokenProvider.validateToken(token);
        if(isAccess) return "yes";
        return "no";
    }


    private void addTokenCookie(HttpServletResponse response, final String cookieName, final String token) {
        ResponseCookie cookie = ResponseCookie.from(cookieName, token)
                .path("/")
                .maxAge(7 * 24 * 60 * 60) // 7 days
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
