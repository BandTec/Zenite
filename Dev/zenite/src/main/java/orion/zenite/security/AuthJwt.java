package orion.zenite.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class AuthJwt {

    public String createToken(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, "jwtSecret")
                .compact();
    }
}
