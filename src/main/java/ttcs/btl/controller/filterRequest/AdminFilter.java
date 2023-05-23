package ttcs.btl.controller.filterRequest;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AdminFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        // Kiểm tra quyền admin ở đây
//        if (isAdmin(request)) {
        System.out.println("request==>"+request.getParameter("to"));
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
