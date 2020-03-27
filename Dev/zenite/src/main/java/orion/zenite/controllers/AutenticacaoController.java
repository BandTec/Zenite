package orion.zenite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import orion.zenite.exceptions.SenhaInvalidaExcepton;
import orion.zenite.dto.LoginRequest;
import orion.zenite.dao.ContaDao;
import orion.zenite.dto.ApiResponse;
import orion.zenite.security.JwtService;
import orion.zenite.security.LoginService;

import javax.validation.Valid;


/*
 * Essa rota não é protegida pelo JWToken
 */
@RestController
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
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            UserDetails usuarioAutenticado = loginService.autenticar(loginRequest);
            String jwtToken = jwt.codificarToken(loginRequest.getEmail());

            return new ApiResponse("Bearer " + jwtToken);
        }
        catch (SenhaInvalidaExcepton | UsernameNotFoundException e) {
             throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
