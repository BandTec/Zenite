package orion.zenite.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.web.filter.OncePerRequestFilter;


/*
 * Classe que intercepta as requisições das rotas
 *
 * O método doFilterInternal verifica se está sendo mandado o header de autenticação
 * e se o token é do tipo e formato certo.
 *
 * Se o token for válido ele é decodificado utilizando a classe JwtService
 * e pegando o email do usuário que é então passado pelo método da classe UsuarioService
 * que retorna um objeto UserDetails que pode ser enviado para o contexto do Spring Security
 * implementado na classe SecurityConfig
 *
 */
public class JwtFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private LoginService loginService;

    public JwtFilter(JwtService jwtService, LoginService loginService) {
        this.jwtService = jwtService;
        this.loginService = loginService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer")) {
            String[] jwtTokenParts = authHeader.split(" ");

            boolean isValid = jwtService.tokenValido(jwtTokenParts[1]);
            if (isValid) {

                String emailUsuario = jwtService.obterEmailDoToken(jwtTokenParts[1]);
                UserDetails usuario = loginService.loadUserByUsername(emailUsuario);

                UsernamePasswordAuthenticationToken usuarioAutenticado =
                        new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                usuarioAutenticado.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usuarioAutenticado);
            }
        }

        filterChain.doFilter(request, response);
    }

}
