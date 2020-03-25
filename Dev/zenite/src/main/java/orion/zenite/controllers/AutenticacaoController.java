package orion.zenite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import orion.zenite.exceptions.SenhaInvalidaExcepton;
import orion.zenite.payload.ApiResponse;
import orion.zenite.payload.LoginRequest;
import orion.zenite.repository.ContaDao;
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
    public ApiResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            UserDetails usuarioAutenticado = loginService.autenticar(loginRequest);
            String jwtToken = jwt.codificarToken(loginRequest.getEmail());

            return new ApiResponse(true, "Bearer " + jwtToken);
        }
        catch (SenhaInvalidaExcepton | UsernameNotFoundException e) {
             return new ApiResponse(false, e.getMessage());
            //throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
