package orion.zenite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orion.zenite.dao.UsuarioDao;
import orion.zenite.models.Usuario;
import orion.zenite.payload.ApiResponse;
import orion.zenite.payload.LoginRequest;
import orion.zenite.security.AuthJwt;

import java.util.List;

@RestController
@RequestMapping("/autentificacao")
public class AutentificacaoController {

    private AuthJwt jwt = new AuthJwt();

    @Autowired
    private UsuarioDao usuarioBD = new UsuarioDao();

    @GetMapping("/usuario")
    public ResponseEntity<?> getAll(){
        List<Usuario> usuarios = usuarioBD.buscarTodos();

        if(usuarios != null) {
            return new ResponseEntity<>(
                    new ApiResponse(true, "uau", usuarios),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Invalid email or password"),
                    HttpStatus.OK);
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        Usuario usuario = usuarioBD.buscarPorId(id);

        if(usuario != null) {
            return new ResponseEntity<>(
                    new ApiResponse(true, "uau uau", usuario),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Invalid email or password"),
                    HttpStatus.OK);
        }
    }

    @PostMapping("/usuario")
    public ResponseEntity<?> inserir(@RequestBody Usuario usuario){
        usuarioBD.inserir(usuario);

        if(usuario != null) {
            return new ResponseEntity<>(
                    new ApiResponse(true, "inserido", usuario),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Invalid email or password"),
                    HttpStatus.OK);
        }
    }

    @PutMapping("/usuario")
    public ResponseEntity<?> alterar(@RequestBody Usuario usuario){
        usuarioBD.alterar(usuario);

        return new ResponseEntity<>(
                new ApiResponse(true, "alterado"),
                HttpStatus.OK);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") int id){
        usuarioBD.deletar(id);


         return new ResponseEntity<>(
            new ApiResponse(true, "deletado"),
            HttpStatus.OK);
    }


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Send all the parameters, email and password."),
                    HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = usuarioBD.buscarPorEmailSenha(loginRequest);

        if(usuario == null) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Invalid email or password"),
                    HttpStatus.OK);
        }

        String jwtToken = jwt.createToken(loginRequest.getEmail());
        return new ResponseEntity<>(
                new ApiResponse(true, "Bearer " + jwtToken),
                HttpStatus.OK);
    }
}
