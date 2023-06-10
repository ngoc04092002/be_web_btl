package ttcs.btl.controller.filterRequest;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ttcs.btl.repository.error.ArgumentException;
import ttcs.btl.service.auth.TokenProvider;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AdminFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;

    @Value("${app.auth.tokenCookieName}")
    public String tokenCookieName;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // Kiểm tra quyền admin ở đây
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        if (tokenCookieName != null && cookies != null) {
            String pathName = httpRequest.getParameter("pathname");

            final var token = getJwtTokenFromCookie(cookies);
            System.out.println("pathName==>"+pathName+"-"+token);
            boolean adminPath = pathName != null ? pathName.contains("admin") : false;
            if (adminPath) {
                if (StringUtils.isBlank(token)) {
                    throw new ArgumentException("Bạn không có quyền truy cập.");
                }
                Claims claims = tokenProvider.decodeJwt(token);
                String role = (String) claims.get("roleCode");

                if (!role.equals("admin")) {
                    throw new ArgumentException("Bạn không có quyền truy cập.");
                }
            }
        }

        chain.doFilter(request, response);
    }

    public String getJwtTokenFromCookie(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (tokenCookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}

