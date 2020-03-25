package orion.zenite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orion.zenite.models.Administrador;
import orion.zenite.models.Conta;
import orion.zenite.models.Endereco;
import orion.zenite.models.Fiscal;
import orion.zenite.payload.ApiResponse;
import orion.zenite.repository.AdministradorDao;
import orion.zenite.repository.ContaDao;
import orion.zenite.repository.DispositivoDao;
import orion.zenite.repository.FiscalDao;

/*
 * Todas as rotas que começam com /api/alguma-coisa
 * estão protegidas pelo JWToken.
 * Todas as URI então recebem o token decodificado
 * como um atributo email da requisição
 *
 * a decodificação ocorre na classe /security/JwtFilter
 */
@RestController
@RequestMapping("/api/administrador")
public class AdministradorController {

    @Autowired
    private ContaDao contaBD;

    @Autowired
    private AdministradorDao administradorBD;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("cadastro")
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity<?> cadastro(@RequestBody Administrador administrador) {

        // PEGANDO INFORMAÇÕES DO USUÁRIO LOGADO
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(user.getAuthorities().toArray()[0]); // pegando nivel do usuário
        System.out.println(user.getName()); // pegando email do usuário

        // inserir conta
        Conta novaConta = administrador.getConta();
        String senhaCriptografada = passwordEncoder.encode(novaConta.getSenha());
        novaConta.setSenha(senhaCriptografada);
        contaBD.save(novaConta);
        administrador.getConta().setIdConta(contaBD.lastId());

        // Fiscal
        administradorBD.save(administrador);
        administrador.setId(administradorBD.lastId());

        return new ResponseEntity<>(
                new ApiResponse(true, "Administrador cadastrado", administrador),
                HttpStatus.OK);
    }
}
