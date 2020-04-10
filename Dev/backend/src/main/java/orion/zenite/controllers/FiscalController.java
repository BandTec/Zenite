package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import orion.zenite.repositorios.FiscalRepository;
import orion.zenite.entidades.*;

import java.util.Optional;


@Api(description = "Operações relacionadas ao fiscal", tags = "fiscal")
@RestController
@RequestMapping("/api/fiscal")
public class FiscalController {

    // Classes que realiza consulta no banco de dados
    @Autowired
    private FiscalRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("Lista todos os fiscais")
    @GetMapping
    public ResponseEntity consulta() {

        if (this.repository.count() > 0) {
            return ResponseEntity.ok(this.repository.findAll());
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @ApiOperation("Buscar um fiscal por seu id")
    @GetMapping("{id}")
    public ResponseEntity consulta(@PathVariable("id") int id) {
        Optional<Fiscal> fiscal = this.repository.findById(id);
        if (fiscal.isPresent()) {
            return ResponseEntity.ok(fiscal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Altera um fiscal")
    @PutMapping
    public ResponseEntity alterar(@RequestBody Fiscal novoFiscal) {
        if (this.repository.existsById(novoFiscal.getId())) {
            // Encriptar senha
            Conta conta = novoFiscal.getConta();
            String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
            conta.setSenha(senhaCriptografada);
            novoFiscal.setConta(conta);

            // alterar fiscal
            this.repository.save(novoFiscal);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Deleta um fiscal por seu id")
    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") int id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Cadastra um fiscal")
    @PostMapping()
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity cadastro(@RequestBody Fiscal novoFiscal) {

        // Encriptar senha
        Conta conta = novoFiscal.getConta();
        String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
        conta.setSenha(senhaCriptografada);
        novoFiscal.setConta(conta);

        // Salvar Fiscal
        this.repository.save(novoFiscal);
        novoFiscal.setId(repository.lastId());

        return ResponseEntity.created(null).build();
    }
}
