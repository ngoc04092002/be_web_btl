package ttcs.btl.controller.auth;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttcs.btl.dto.auth.AuthRequest;
import ttcs.btl.dto.auth.AuthResponse;
import ttcs.btl.dto.auth.UserResponse;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.repository.error.ArgumentException;
import ttcs.btl.repository.error.ResourceFoundException;
import ttcs.btl.repository.error.ResourceNotFoundException;
import ttcs.btl.service.auth.IAuthService;
import ttcs.btl.service.auth.TokenProvider;

import java.util.Optional;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/", produces = "application/json")
public class AuthController {

    private final PasswordEncoder passwordEncoder;

    private final IAuthService iAuthService;
    private final TokenProvider tokenProvider;

    @Value("${app.auth.tokenCookieName}")
    public String tokenCookieName;

    @PostMapping("sign-up")
    public String createUser(@RequestBody ClientEntity clientEntity) {
        try {
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
            throw new ResourceNotFoundException(email);
        }

        boolean isMatcher = passwordEncoder.matches(password, user.getPassword());
        if (!isMatcher) {
            throw new ResourceNotFoundException(email);
        } else {
            final var token = tokenProvider.createJwtToken(email);
            addTokenCookie(response, tokenCookieName, token);
            return new AuthResponse(user, token);
        }
    }


    private void addTokenCookie(HttpServletResponse response, final String cookieName, final String token) {
        ResponseCookie cookie = ResponseCookie.from(cookieName, token)
                .path("/")
                .maxAge(4 * 24 * 60 * 60) // 4 days
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
