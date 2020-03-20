package orion.zenite.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("development")
public class DevelopmentConfiguration {

    @Bean
    public CommandLineRunner executar() {
        return args -> {
            System.out.println("Perfil de Desenvolvimento");
        };
    }
}
