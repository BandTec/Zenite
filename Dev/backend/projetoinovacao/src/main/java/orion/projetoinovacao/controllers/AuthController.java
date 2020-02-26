package orion.projetoinovacao.controllers;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orion.projetoinovacao.model.Funcionario;
import orion.projetoinovacao.payload.ApiResponse;
import orion.projetoinovacao.payload.LoginRequest;
import orion.projetoinovacao.repository.FuncionarioRepository;

import javax.servlet.ServletException;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            return new ResponseEntity(new ApiResponse(false, "Preencha o email e a senha"), HttpStatus.BAD_REQUEST);
        }

        Funcionario f = funcionarioRepository.findByEmail(loginRequest.getEmail());
        if(f == null) {
            return new ResponseEntity(new ApiResponse(false, "Credencias não encontradas"), HttpStatus.OK);
        }

        if(!loginRequest.getPassword().equals(f.getPassword())){
            return new ResponseEntity(new ApiResponse(false, "Credencias inválidas."), HttpStatus.OK);
        }

        String jwtToken = "tokem";
//        String jwtToken = Jwts.builder()
//                .setSubject(loginRequest.getEmail())
//                .signWith(SignatureAlgorithm.HS256, "jwtSecret")
//                .compact();


        return ResponseEntity.ok(new ApiResponse(true, jwtToken));
    }

}
