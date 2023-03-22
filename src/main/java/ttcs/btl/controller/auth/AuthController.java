package ttcs.btl.controller.auth;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
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
import ttcs.btl.service.auth.IAuthService;
import ttcs.btl.service.auth.TokenProvider;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/", produces = "application/json")
public class AuthController {
    private final String defaultPasswordSignInWithSocial = "12345678";
    private final PasswordEncoder passwordEncoder;

    private final IAuthService iAuthService;
    private final TokenProvider tokenProvider;

    @Value("${app.auth.tokenCookieName}")
    public String tokenCookieName;


    @GetMapping("get-user-info")
    public AuthResponse getUserInfo(@RequestParam String access) {
        Claims claims = tokenProvider.decodeJwt(access);
        String email = claims.getSubject();
        final var user = iAuthService.fetchUser(email);

        if (user == null) {
            throw new ResourceNotFoundException(email);
        }
        return new AuthResponse(user, access);
    }

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

    @PostMapping("sign-in-social")
    public AuthResponse authResponseWithSocial(@RequestBody @Valid AuthRequestSocial authRequestSocial,
            HttpServletResponse response) {
        final String email = authRequestSocial.getEmail();
        final var user = iAuthService.fetchUser(email);
        final var token = tokenProvider.createJwtToken(email);
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

    @GetMapping("/refresh-cookie")
    public String getCookie(HttpServletRequest request) {
        String setCookie = request.getHeader(HttpHeaders.COOKIE);
        if(setCookie.contains(tokenCookieName)) return "yes";
        return "no";
    }


    private void addTokenCookie(HttpServletResponse response, final String cookieName, final String token) {
        ResponseCookie cookie = ResponseCookie.from(cookieName, token)
                .path("/")
                .maxAge(4*24*60*60) // 4 days
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
