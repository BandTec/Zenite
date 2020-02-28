package orion.projetoinovacao.controllers;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orion.projetoinovacao.model.Funcionario;
import orion.projetoinovacao.payload.ApiResponse;
import orion.projetoinovacao.payload.LoginRequest;
import orion.projetoinovacao.repository.FuncionarioRepository;
import orion.projetoinovacao.security.AuthJwt;

import javax.servlet.ServletException;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    private AuthJwt jwt = new AuthJwt();

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            return new ResponseEntity(
                    new ApiResponse(false,"Preencha o email e a senha"),
                    HttpStatus.BAD_REQUEST);
        }

        Funcionario funcionario = funcionarioRepository.findByEmail(loginRequest.getEmail());
        if(funcionario == null) {
            return new ResponseEntity(
                    new ApiResponse(false, "Credencias não encontradas"),
                    HttpStatus.OK);
        }

        if(!loginRequest.getPassword().equals(funcionario.getPassword())){
            return new ResponseEntity(
                    new ApiResponse(false, "Credencias inválidas."),
                    HttpStatus.OK);
        }

        String jwtToken = jwt.createToken(loginRequest.getEmail());

        return new ResponseEntity(
                new ApiResponse(true, "Bearer " + jwtToken),
                HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(){

        return new ResponseEntity(
                new ApiResponse(true, "Request Completed"),
                HttpStatus.OK);
    }


}
