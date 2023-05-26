package ttcs.btl.controller.filterRequest;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ttcs.btl.service.auth.TokenProvider;

import java.io.IOException;
import java.util.Enumeration;

@Component
@RequiredArgsConstructor
public class AdminFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Kiểm tra quyền admin ở đây
//        if (isAdmin(request)) {

        Enumeration<String> headerNames = request.getHeaderNames();

        if (headerNames != null) {
            String token = request.getHeader("access-token");
            String pathName = request.getHeader("Path-Name");
            if (StringUtils.isNotBlank(token)) {
                Claims claims = tokenProvider.decodeJwt(token);
                String role = (String) claims.get("roleCode");

                System.out.println("role==>" + role);
            }


            System.out.println("Token==>" + token + "-" + pathName);
        }
//        if (StringUtils.isNotBlank(token)) {


        filterChain.doFilter(request, response);
//        } else {
//            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập.");
//        }
    }

    private boolean isAdmin(HttpServletRequest request) {
        // Kiểm tra quyền admin ở đây, ví dụ:
        String username = request.getParameter("username");
        return "admin".equals(username);
    }
}
