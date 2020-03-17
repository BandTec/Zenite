package orion.zenite.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthJwt {

    /*
        * Método que recebe uma String e a codifica utilizando o código
        * que está no arquivo:
*                        ~/main/resources/application.properties
     */
    public String createToken(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, "jwtSecret")
                .compact();
    }
}
