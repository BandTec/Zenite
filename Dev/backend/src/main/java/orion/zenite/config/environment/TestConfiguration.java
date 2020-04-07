package orion.zenite.config.environment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfiguration {

    @Bean
    public CommandLineRunner executar() {
        return args -> {
            System.out.println("Perfil de Teste");
        };
    }
}
