package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import orion.zenite.entidades.Administrador;
import orion.zenite.entidades.Conta;
import orion.zenite.repositorios.AdministradorRepository;

import java.util.Optional;

/*
 * Todas as rotas que começam com /api/alguma-coisa
 * estão protegidas pelo JWToken.
 * Todas as URI então recebem o token decodificado
 * como um atributo email da requisição
 *
 * a decodificação ocorre na classe config/security/JwtFilter
 */
@Api(description = "Operações relacionados ao administrador", tags = "administrador")
@RestController
@RequestMapping("/api/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
        // PEGANDO INFORMAÇÕES DO USUÁRIO LOGADO
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(user.getAuthorities().toArray()[0]); // pegando nivel do usuário
        System.out.println(user.getName()); // pegando email do usuário

     */

    @ApiOperation("Lista todos os administradores")
    @GetMapping
    public ResponseEntity consultar(){
        if (this.repository.count() > 0) {
            return ResponseEntity.ok(this.repository.findAll());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @ApiOperation("Buscar um administrador por seu id")
    @GetMapping("{id}")
    public ResponseEntity consultar(@PathVariable("id") int id){
        Optional<Administrador> adm = this.repository.findById(id);
        if (adm.isPresent()) {
            return ResponseEntity.ok(adm);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Deleta um administrador por seu id")
    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") int id){
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Altera um administrador")
    @PutMapping
    public ResponseEntity alterar(@RequestBody Administrador administrador){
        if (this.repository.existsById(administrador.getId())) {
            // encriptar senha
            Conta conta = administrador.getConta();
            String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
            conta.setSenha(senhaCriptografada);
            administrador.setConta(conta);

            // altera adm
            this.repository.save(administrador);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Cadastra um administrador")
    @PostMapping
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity cadastrar(@RequestBody Administrador administrador) {
        Conta conta = administrador.getConta();

        // Encriptar senha
        String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
        conta.setSenha(senhaCriptografada);
        administrador.setConta(conta);

        // salvar adm
        this.repository.save(administrador);

        return ResponseEntity.created(null).build();
    }
}
