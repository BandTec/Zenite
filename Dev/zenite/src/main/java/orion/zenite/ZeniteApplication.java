package orion.zenite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import orion.zenite.security.JwtFilter;

@SpringBootApplication
public class ZeniteApplication {

	/*
		* Método abaixo filtra todas as requisições que começam com a uri --> api/
		* Podem ser adicionadas outras rotdas da seguinte forma:
		*
		* Ex -> registrationBean.addUrlPatterns("/api/*", "outra-rota/uri", "/roteando");
	 */
	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {
		final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<JwtFilter>();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");

		return registrationBean;
	}

	// Método padrão de inicialização do springboot
	public static void main(String[] args) {
		SpringApplication.run(ZeniteApplication.class, args);
	}

}
