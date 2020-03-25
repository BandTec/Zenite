package orion.zenite.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    // * Valor que está no arquivo ~/main/resources/application.properties
    @Value("${app.jwtExpirationInMs}")
    private int expiracao;

    // * Valor que está no arquivo ~/main/resources/application.properties
    @Value("${app.jwtSecret}")
    private String chaveAssinatura;

    // Método que cria o token
    // No token é adicionado:
    //      * o email do usuário,
    //      * a data de expiração do token
    // e é assinado com a chave do aplicativo
    public String codificarToken(String subject){
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expiracao);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date dataExpiracao = Date.from(instant);

        /*
        HashMap<String, Object> informacoesToken = new HashMap<>();
        informacoesToken.put("emaildousuario", "email@asd.com");
        informacoesToken.put("niveis", "admin");
         */

        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(dataExpiracao)
                //.setClaims(informacoesToken)
                .signWith(SignatureAlgorithm.HS256, chaveAssinatura)
                .compact();
    }

    // Método que decodifica o token
    private Claims decodificarToken(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    // Método que obtem o email do token após decodificado
    public String obterEmailDoToken(String token) throws ExpiredJwtException{
        return decodificarToken(token).getSubject();
    }

    // Método que verifica data de expiração do token
    public boolean tokenValido(String token){
        try{
            Claims informacoes = decodificarToken(token);
            Date dataExpiracao = informacoes.getExpiration();
            LocalDateTime dataDoToken = dataExpiracao
                                    .toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDateTime();

            // Se a data atual ultrapassar a data do token então ele não está mais válido
            return !LocalDateTime.now().isAfter(dataDoToken);

        }catch (Exception e){
            return false;
        }
    }
}
