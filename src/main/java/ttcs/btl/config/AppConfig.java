package ttcs.btl.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ttcs.btl.controller.filterRequest.AdminFilter;

@Configuration
public class AppConfig {
    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilterRegistration() {
        FilterRegistrationBean<AdminFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AdminFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }
}
