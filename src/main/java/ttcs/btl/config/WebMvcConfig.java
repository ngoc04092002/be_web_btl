package ttcs.btl.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ttcs.btl.config.EnvironmentConfig;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final EnvironmentConfig environmentConfig;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/api/v1/**")
                .allowedOriginPatterns(environmentConfig.getCorsEndPoint().toArray(String[]::new))
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE");
    }
}
