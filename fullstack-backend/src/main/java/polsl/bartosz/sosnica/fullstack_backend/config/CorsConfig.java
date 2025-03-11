package polsl.bartosz.sosnica.fullstack_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class to define Cross-Origin Resource Sharing (CORS) settings
 * for the application.
 * <p>
 * This configuration allows frontend applications hosted on
 * "http://localhost:4200" to communicate
 * with the backend by enabling CORS for specific HTTP methods and headers. It
 * ensures that the browser
 * does not block cross-origin requests necessary for frontend-backend
 * interaction.
 * </p>
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configures CORS settings for the entire application.
     * <p>
     * This method sets up global CORS rules, allowing requests from
     * "http://localhost:4200" with
     * specific HTTP methods and headers. It also enables credentials (such as
     * cookies and authorization headers)
     * to be sent along with requests.
     * </p>
     *
     * @param registry the CorsRegistry object that manages CORS configurations.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}
