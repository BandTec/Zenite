package orion.zenite.controllers;


import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import orion.zenite.exceptions.SenhaInvalidaExcepton;
import orion.zenite.dto.LoginRequest;
import orion.zenite.dao.ContaDao;
import orion.zenite.dto.ResponstaApi;
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
    private ContaDao contaBD;

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    @ApiOperation("Autenticar credenticiais no sistema")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário autenticado"),
            @ApiResponse(code = 401, message = "Usuário não encontrado / Senha Inválida.")
    })
    public ResponstaApi login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            UserDetails usuarioAutenticado = loginService.autenticar(loginRequest);
            String jwtToken = jwt.codificarToken(loginRequest.getEmail());

            return new ResponstaApi("Bearer " + jwtToken);
        }
        catch (SenhaInvalidaExcepton | UsernameNotFoundException e) {
             throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
