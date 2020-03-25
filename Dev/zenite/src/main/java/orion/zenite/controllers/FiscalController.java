package orion.zenite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import orion.zenite.models.*;
import orion.zenite.payload.ApiResponse;
import orion.zenite.repository.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/*
 * Todas as rotas que começam com /api/alguma-coisa
 * estão protegidas pelo JWToken.
 * Todas as URI então recebem o token decodificado
 * como um atributo email da requisição
 *
 * a decodificação ocorre na classe /security/JwtFilter
 */
@RestController
@RequestMapping("/api/fiscal")
public class FiscalController {

    // Classes que realiza consulta no banco de dados
    @Autowired
    private EnderecoDao enderecoBD;

    @Autowired
    private ContaDao contaBD;

    @Autowired
    private FiscalDao fiscalBD;

    @Autowired
    private DispositivoDao dispositivoBD;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("consulta")
    public ResponseEntity<?> consulta(ServletRequest req) {

        return new ResponseEntity<>(
                new ApiResponse(true, "Requisição concluída com sucesso.", fiscalBD.findAll()),
                HttpStatus.OK);

    }

    @GetMapping("consulta/{id}")
    public ResponseEntity<?> consulta(ServletRequest req, @PathVariable("id") int id) {

        return new ResponseEntity<>(
                new ApiResponse(
                        true,
                        "Requisição concluída com sucesso.",
                        fiscalBD.findById(id)
                ),
                HttpStatus.OK);

    }

    @PostMapping("cadastro")
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity<?> cadastro(@RequestBody Fiscal fiscal) {

        // inserir conta
        Conta novaConta = fiscal.getConta();
        String senhaCriptografada = passwordEncoder.encode(novaConta.getSenha());
        novaConta.setSenha(senhaCriptografada);
        contaBD.save(novaConta);
        fiscal.getConta().setIdConta(contaBD.lastId());

        // inserir endereco
        Endereco endereco = fiscal.getEndereco();
        enderecoBD.save(endereco);
        endereco.setId(enderecoBD.lastId());
        fiscal.setEndereco(endereco);

        //Dispositivo
        dispositivoBD.save(fiscal.getDispositivo());
        fiscal.getDispositivo().setId(dispositivoBD.lastId());

        // Fiscal
        fiscalBD.save(fiscal);
        fiscal.setId(fiscalBD.lastId());

        return new ResponseEntity<>(
                new ApiResponse(true, "Fiscal cadastrado", fiscal),
                HttpStatus.OK);
    }
}
