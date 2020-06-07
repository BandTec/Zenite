package orion.zenite.controllers;


import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import orion.zenite.exceptions.SenhaInvalidaExcepton;
import orion.zenite.modelos.LoginRequest;
import orion.zenite.repositorios.ContaRepository;
import orion.zenite.modelos.ResponstaApi;
import orion.zenite.config.security.JwtService;
import orion.zenite.config.security.LoginService;

import javax.validation.Valid;


/*
 * Essa rota não é protegida pelo JWToken
 */
@Api(description = "Autentificacao para as outras rotas", tags = "Autentificacao")
@RestController
//@CrossOrigin("http://localhost:3000")
@RequestMapping("/autentica")
public class AutenticacaoController {

    // Classe que gera JWToken
    @Autowired
    private JwtService jwt;

    // Classe que realiza consulta no banco de dados
    @Autowired
    private ContaRepository contaBD;

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    @ApiOperation("Autenticar credenticiais no sistema")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário autenticado"),
            @ApiResponse(code = 401, message = "Usuário não encontrado / Senha Inválida.")
    })
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            UserDetails usuarioAutenticado = loginService.autenticar(loginRequest);
            String jwtToken = jwt.codificarToken(loginRequest.getEmail());

            return ResponseEntity.ok(new ResponstaApi("Bearer " + jwtToken));
        }
        catch (SenhaInvalidaExcepton | UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponstaApi(e.getMessage()));
             //throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
