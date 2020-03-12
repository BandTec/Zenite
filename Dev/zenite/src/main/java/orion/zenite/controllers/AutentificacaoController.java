package orion.zenite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orion.zenite.dao.ContaDao;
import orion.zenite.models.Conta;
import orion.zenite.models.Nivel;
import orion.zenite.payload.ApiResponse;
import orion.zenite.payload.CadastroRequest;
import orion.zenite.payload.LoginRequest;
import orion.zenite.security.AuthJwt;

import javax.validation.Valid;
import java.util.List;


/*
    * Essa rota não é protegida pelo JWToken
 */
@RestController
@RequestMapping("/autentica")
public class AutentificacaoController {

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



    @PostMapping("teste-cadastro-conta")
    public ResponseEntity<?> testeCadastroConta(@RequestBody CadastroRequest conta) {

        Nivel n = Nivel.escolherPorId(conta.getIdNivel());
        Conta novaConta = new Conta();
        novaConta.setSenha(conta.getSenha());
        novaConta.setEmail(conta.getEmail());
        novaConta.setNivel(n);

        boolean resultado = contaBD.inserir(novaConta);
        if(resultado) {
            return new ResponseEntity<>(
                    new ApiResponse(true, "Teste"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Erro ao inserir no banco", novaConta),
                    HttpStatus.OK);
        }
    }


}
