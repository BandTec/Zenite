package orion.projetoinovacao.controllers;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orion.projetoinovacao.model.Usuario;
import orion.projetoinovacao.payload.ApiResponse;
import orion.projetoinovacao.payload.LoginRequest;
import orion.projetoinovacao.repository.UsuarioRepository;
import orion.projetoinovacao.security.AuthJwt;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private boolean isLooged;

    private AuthJwt jwt = new AuthJwt();

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            return new ResponseEntity(
                    new ApiResponse(false,"Send all the parameters, email and password."),
                    HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = usuarioRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if(usuario == null) {
            return new ResponseEntity(
                    new ApiResponse(false, "Invalid email or password"),
                    HttpStatus.OK);
        }

        String jwtToken = jwt.createToken(loginRequest.getEmail());
        isLooged = true;
        return new ResponseEntity(
                new ApiResponse(true, "Bearer " + jwtToken),
                HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(){
        if(isLooged) {
            isLooged = false;
            return new ResponseEntity(
                    new ApiResponse(true, "Logout completado!"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(
                    new ApiResponse(false, "Usuário não estava Logado"),
                    HttpStatus.OK);
        }
    }


}
