package orion.projetoinovacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import orion.projetoinovacao.security.JwtFilter;

@SpringBootApplication
public class ProjetoinovacaoApplication {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/usuarios/consulta");

		return registrationBean;
	}


	public static void main(String[] args) {
		SpringApplication.run(ProjetoinovacaoApplication.class, args);
	}

}
