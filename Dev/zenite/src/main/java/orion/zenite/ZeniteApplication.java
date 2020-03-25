package orion.zenite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import orion.zenite.security.JwtFilter;

@SpringBootApplication
public class ZeniteApplication {

	// Método padrão de inicialização do springboot
	public static void main(String[] args) {
		SpringApplication.run(ZeniteApplication.class, args);
	}

}
