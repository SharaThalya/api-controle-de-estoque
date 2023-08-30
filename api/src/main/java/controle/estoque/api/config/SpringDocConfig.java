package controle.estoque.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    // url swagger = http://localhost:8080/swagger-ui/index.html
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("ESTOQUE REST API")
                .description("API CONTROLE ESTOQUE")
                .version("1.0"));
    }
}
