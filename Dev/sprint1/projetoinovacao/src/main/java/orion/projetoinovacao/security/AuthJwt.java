package orion.projetoinovacao.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class AuthJwt {

    public String createToken(String subject){
        String jwt = Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, "jwtSecret")
                .compact();

        return jwt;
    }
}
