package ttcs.btl.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ttcs.btl.controller.filterRequest.AdminFilter;
import ttcs.btl.service.auth.TokenProvider;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final TokenProvider tokenProvider;
    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilterRegistration() {
        FilterRegistrationBean<AdminFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AdminFilter(tokenProvider));
        registration.addUrlPatterns("/*");
        return registration;
    }
}
