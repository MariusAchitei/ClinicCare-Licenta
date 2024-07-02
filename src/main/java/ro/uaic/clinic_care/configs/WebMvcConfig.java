package ro.uaic.clinic_care.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ro.uaic.clinic_care.filters.JwtRequestFilter;


/**
 * Class responsible for configuring the Api Security and CORS
 * @author Marius Achitei, truiID (Pty) Ltd, 2024
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtRequestFilter()).order(1);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .exposedHeaders("*").allowedOrigins("http://localhost:3000/").allowedMethods("*").allowCredentials(false);
    }
}