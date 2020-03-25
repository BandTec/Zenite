package orion.zenite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtFilter(jwtService, loginService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Autentica as credenciais através da classe UsuarioService
        // que implementa a interface UserDetailsService

        auth.userDetailsService(loginService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
            Método abaixo filtra todas as requisições utilizando o filtro jwtFilter
            e verifica se possuem o nivel de autorização necessário para acesso
                     * /autentica está liberada para qualquer acesso sem autenticação
                     * a /api/* precisa ter o nivel de autorização "ADMIN", "GERENTE", "FISCAL", "MOTORISTA", "PASSAGEIRO"
                     * qualquer outra rota precisa ter sido pelo menos autenticada (o usuario deve existir no banco)
        */
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/administrador/**")
                    .hasAnyRole("ADMIN")
                .antMatchers("/api/fiscal/**")
                    .hasAnyRole("ADMIN", "GERENTE")
                .antMatchers("/api/motorista**")
                    .hasAnyRole("ADMIN", "GERENTE")
                .antMatchers("/api/gerente/**")
                    .hasAnyRole("ADMIN", "GERENTE", "MOTORISTA", "PASSAGEIRO")
                .antMatchers("/api/viagem/**")
                .hasAnyRole("ADMIN", "GERENTE", "FISCAL", "MOTORISTA", "PASSAGEIRO")
                .antMatchers("/autentica/**")
                    .permitAll()
               // .anyRequest()
               //    .authenticated()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
