package ttcs.btl.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class TokenProvider {
    @Value("${app.auth.tokenSecret}")
    public String tokenSecret;

    @Value("${app.auth.tokenExpirationMsec}")
    public Long tokenExpirationMsec;

    public String createJwtToken(String email) {
        return buildJwtToken(email);
    }

    private String buildJwtToken(String email) {
        final var now = new Date();
        final var expireDate = new Date(now.getTime() + tokenExpirationMsec);
        Claims claims = Jwts.claims()
                .setSubject(email);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(Keys.hmacShaKeyFor(tokenSecret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }
}
