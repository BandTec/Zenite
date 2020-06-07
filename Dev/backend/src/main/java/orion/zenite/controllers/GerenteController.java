package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import orion.zenite.modelos.ConsultaPaginada;
import orion.zenite.entidades.Conta;
import orion.zenite.entidades.Gerente;
import orion.zenite.repositorios.GerenteRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Api(description = "Operações relacionadas ao gerente", tags = "gerente")
@RestController
@RequestMapping("/api/gerente")
public class GerenteController {

    @Autowired
    private GerenteRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("Lista todos os gerentes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping
    public ResponseEntity consulta( @RequestParam(required = false) Integer pagina,
                                    @RequestParam(required = false) String q
    )  {
        if (this.repository.count() > 0) {
            if(pagina != null) {
                Pageable pageable = PageRequest.of(pagina, 10);
                Page<Gerente> page = repository.findAll(pageable);
                ConsultaPaginada consulta = new ConsultaPaginada(page);
                return ok(consulta);
            }
            else if(q != null){
                List<Gerente> consulta = repository.findAllByNomeContaining(q);
                return ok(consulta);
            }
            else {
                List<Gerente> consulta = repository.findAll();
                return ok(consulta);
            }
        } else {
            return noContent().build();
        }
    }

    @ApiOperation("Busca gerente pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("{id}")
    public ResponseEntity consulta(@PathVariable("id") Integer id){
        Optional<Gerente> consultaGerente = this.repository.findById(id);

        if(consultaGerente.isPresent()){
            return ok(consultaGerente);
        }else{
            return notFound().build();
        }
    }

    @ApiOperation("Altera um gerente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Gerente não encontrado.")
    })
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity alterar(@RequestBody Gerente novoGerente,
                        @PathVariable Integer id){
        if(this.repository.existsById(id)) {
            novoGerente.setId(id);
            Conta conta = novoGerente.getConta();
            String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
            conta.setSenha((senhaCriptografada));
            novoGerente.setConta(conta);
            repository.save(novoGerente);
            return ok().build();
        }else{
            return notFound().build();
        }
    }

    @ApiOperation("Deleta um gerente por seu ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Gerente não encontrado.")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deletar(@PathVariable("id") Integer id){
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @ApiOperation("Cadastra um Motorista")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Necessário ajustes no corpo da requisição.")
    })
    @PostMapping()
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity cadastro(@RequestBody Gerente novoGerente){
        Conta conta = novoGerente.getConta();
        String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
        conta.setSenha((senhaCriptografada));
        novoGerente.setConta(conta);
        repository.save(novoGerente);
        novoGerente.setId(repository.lastId());

        return created(null).build();
    }

}
