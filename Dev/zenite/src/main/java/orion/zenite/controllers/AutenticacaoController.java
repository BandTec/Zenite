package orion.zenite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orion.zenite.dao.ContaDao;
import orion.zenite.models.Conta;
import orion.zenite.payload.ApiResponse;
import orion.zenite.payload.LoginRequest;
import orion.zenite.security.AuthJwt;



/*
    * Essa rota não é protegida pelo JWToken
 */
@RestController
@RequestMapping("/autentica")
public class AutenticacaoController {

    // Classe que gera JWToken
    private AuthJwt jwt = new AuthJwt();

    // Classe que realiza consulta no banco de dados
    @Autowired
    private ContaDao contaBD = new ContaDao();


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        if (loginRequest.getEmail() == null || loginRequest.getSenha() == null) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Necessário os parâmetros email e senha."),
                    HttpStatus.BAD_REQUEST);
        }

        Conta usuario = contaBD.buscarPorEmailSenha(loginRequest);

        if(usuario == null) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Email ou/e senha inválidos"),
                    HttpStatus.OK);
        }

        String jwtToken = jwt.createToken(loginRequest.getEmail());
        return new ResponseEntity<>(
                new ApiResponse(true, "Bearer " + jwtToken),
                HttpStatus.OK);
    }

}
