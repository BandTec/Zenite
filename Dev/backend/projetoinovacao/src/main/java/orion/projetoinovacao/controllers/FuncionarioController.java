package orion.projetoinovacao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orion.projetoinovacao.model.Funcionario;
import orion.projetoinovacao.payload.ApiResponse;
import orion.projetoinovacao.repository.FuncionarioRepository;
import orion.projetoinovacao.security.AuthJwt;

import javax.validation.Valid;


@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    private AuthJwt jwt = new AuthJwt();


    @PostMapping("/cadastro")
    public ResponseEntity cadastrar(@Valid @RequestBody Funcionario funcionario) {

        Boolean userExists = repository.existsByEmail(funcionario.getEmail());
        if(userExists) {
            return new ResponseEntity(
                    new ApiResponse(false, "User already exists with this email."),
                    HttpStatus.BAD_REQUEST);
        } else {
            repository.save(funcionario);
            return new ResponseEntity(
                    new ApiResponse(true, "Request completed",
                            repository.findByEmail(funcionario.getEmail())),
                    HttpStatus.OK);
        }
    }

    @GetMapping("/consulta")
    public ResponseEntity consultar(@RequestHeader("authorization") String authHeader) {
        ApiResponse response = jwt.readToken(authHeader);

        if (!response.getSucess()) {
            return new ResponseEntity(
                    response,
                    HttpStatus.BAD_REQUEST);
        }

        Funcionario funcionario = repository.findByEmail(response.getMessage());
        if (funcionario != null) {
            return new ResponseEntity(
                    new ApiResponse(true, "Request completed", repository.findAll()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(
                    new ApiResponse(true, "Unauthorized"),
                    HttpStatus.UNAUTHORIZED);
        }
    }

}
