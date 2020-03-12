package orion.zenite.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {

    /*
     * Método que intercepta as requisições das rotas
     * o método verifica se está sendo mandado o header de autenticação
     * e se esse está mandando o token no formato certo e no tipo certo
     * se o token for válido ele é decodificado utilizando o jwtSecret
     * que está no arquivo:
     *                        ~/main/resources/application.properties
     * com o token decodificado o email do usuário é adicionado a requisição
     * como um atributo que pode ser acessado nos métodos do Controller
     * usando o nome do atributo que é email (em minúsculo).
     */
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

            chain.doFilter(req, res);
        } else {

            if (authHeader == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Authorization header needed");
                return ;
            }

            String[] jwtTokenParts = authHeader.split(" ");

            if (jwtTokenParts.length != 2) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid Token format");
                return ;
            }

            if (authHeader.equals("Bearer")) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid Token type");
                return ;
            }

            try {
                // DECODIFICAÇÃO DO TOKEN
                final Claims tokenOpen = Jwts.parser()
                        .setSigningKey("jwtSecret")
                        .parseClaimsJws(jwtTokenParts[1])
                        .getBody();

                String email = tokenOpen.getSubject();

                // ADICIONANDO ATRIBUTO A REQUISIÇÃO
                request.setAttribute("email", email);

            } catch (final SignatureException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Invalid token");
                return ;
            }

            chain.doFilter(req, res);
        }
    }
}