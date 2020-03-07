package orion.projetoinovacao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orion.projetoinovacao.model.Usuario;
import orion.projetoinovacao.payload.ApiResponse;
import orion.projetoinovacao.repository.UsuarioRepository;
import orion.projetoinovacao.security.AuthJwt;

import javax.validation.Valid;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    private AuthJwt jwt = new AuthJwt();


    @PostMapping("/cadastro")
    public ResponseEntity cadastrar(@Valid @RequestBody Usuario usuario) {
        if(usuario.getEmail() == null || usuario.getName() == null || usuario.getPassword() == null){
            return new ResponseEntity(
                    new ApiResponse(false, "Please send all the parameters."),
                    HttpStatus.BAD_REQUEST);
        }
        Boolean userExists = repository.existsByEmail(usuario.getEmail());
        if(userExists) {
            return new ResponseEntity(
                    new ApiResponse(false, "User already exists with this email."),
                    HttpStatus.BAD_REQUEST);
        } else {
            repository.save(usuario);
            return new ResponseEntity(
                    new ApiResponse(true, "Request completed",
                            repository.findByEmail(usuario.getEmail())),
                    HttpStatus.OK);
        }
    }

    @GetMapping("/consulta")
    public ResponseEntity consultar(@RequestAttribute("email") String email) {

            Usuario usuario = repository.findByEmail(email);
            if (usuario != null) {
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
