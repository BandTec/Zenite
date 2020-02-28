package orion.projetoinovacao.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import orion.projetoinovacao.payload.ApiResponse;



public class AuthJwt {

    public String createToken(String subject){
        String jwt = Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, "jwtSecret")
                .compact();

        return jwt;
    }


    public ApiResponse readToken(String authHeader){
        try {

            if (authHeader == null) {
                return new ApiResponse(false, "Token not found");
            }

            String[] jwtTokenParts = authHeader.split(" ");

            if (jwtTokenParts.length != 2) {
                return new ApiResponse(false, "Invalid Token format");
            }

            if (authHeader.equals("Bearer")) {
                return new ApiResponse(false, "Invalid Token type");
            }

            final Claims tokenOpen = Jwts.parser()
                    .setSigningKey("jwtSecret")
                    .parseClaimsJws(jwtTokenParts[1])
                    .getBody();

            String email = tokenOpen.getSubject();

            return new ApiResponse(true, email);
        } catch (Exception e) {
            return new ApiResponse(false, e.getMessage());
       }
    }
}
