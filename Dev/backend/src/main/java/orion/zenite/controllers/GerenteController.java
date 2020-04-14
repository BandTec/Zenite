package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import orion.zenite.entidades.Conta;
import orion.zenite.entidades.Gerente;
import orion.zenite.repositorios.GerenteRepository;

import java.util.List;

@Api(description = "Operações relacionadas ao gerente", tags = "gerente")
@RestController
@RequestMapping("/api/gerente")
public class GerenteController {

    @Autowired
    private GerenteRepository gerenteDB;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("Lista todos os gerentes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping
    public List<Gerente> consulta() {

        List<Gerente> lista = gerenteDB.findAll();
        if(!lista.isEmpty()){
            return lista;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sua requisição não retornou dados");
    }

    @ApiOperation("Busca gerente pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("{id}")
    public Gerente consulta(@PathVariable("id") int id){
        return gerenteDB
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Gerente não encontrado"));
    }

    @ApiOperation("Altera um gerente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Gerente não encontrado.")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@RequestBody Gerente novoGerente){
        Conta conta = novoGerente.getConta();
        String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
        conta.setSenha((senhaCriptografada));
        novoGerente.setConta(conta);
        gerenteDB.save(novoGerente);
    }

    @ApiOperation("Deleta um gerente por seu ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Gerente não encontrado.")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") int id){
        gerenteDB.findById(id)
                .map( carro -> {
                    gerenteDB.delete(carro);
                    return carro;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Gerente não encontrado"));
    }

    @ApiOperation("Cadastra um Motorista")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Necessário ajustes no corpo da requisição.")
    })
    @PostMapping()
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public Gerente cadastro(@RequestBody Gerente novoGerente){
        Conta conta = novoGerente.getConta();
        String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
        conta.setSenha((senhaCriptografada));
        novoGerente.setConta(conta);
        gerenteDB.save(novoGerente);
        novoGerente.setId(gerenteDB.lastId());

        return novoGerente;
    }

}
