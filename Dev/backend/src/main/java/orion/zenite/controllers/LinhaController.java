package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orion.zenite.entidades.Linha;
import orion.zenite.entidades.PontoFinal;
import orion.zenite.repositorios.LinhaRepository;

import java.util.List;


@Api(description = "Operações relacionadas ao linha", tags = "linha")
@RestController
@RequestMapping("/api/linha")
public class LinhaController {

    @Autowired
    private LinhaRepository linhaBD;

    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @ApiOperation("Lista todas as linhas de ônibus")
    @GetMapping
    public ResponseEntity consulta() {
        if (this.linhaBD.count() > 0) {
            return ResponseEntity.ok(this.linhaBD.findAll());
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    @ApiOperation("Busca linha pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("{id}")
    public Linha consulta(@PathVariable("id") int id){
        return linhaBD
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Linha não encontrado"));
    }

    @ApiOperation("Busca ponto ida pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/pontoIda/{id}")
    public List<Linha> consultaPorPontoIda(@PathVariable("id") int id){
        PontoFinal p = new PontoFinal();
        p.setId(id);
        List<Linha> lista = linhaBD.findAllByPontoIda(p);
        if(!lista.isEmpty()){
            return lista;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrada nenhuma linha com este ponto inicial.");

    }

    @ApiOperation("Busca ponto volta pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/pontoVolta/{id}")
    public List<Linha> consultaPorPontoVolta(@PathVariable("id") int id){
        PontoFinal p = new PontoFinal();
        p.setId(id);
        List<Linha> lista = linhaBD.findAllByPontoVolta(p);
        if(!lista.isEmpty()){
            return lista;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrada nenhuma linha com este ponto inicial.");

    }

    @ApiOperation("Altera uma linha")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Linha não encontrada.")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@RequestBody Linha novaLinha){
        linhaBD.save(novaLinha);
    }

    @ApiOperation("Deleta uma linha por seu ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Linha não encontrado.")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") int id){
        linhaBD.findById(id)
                .map( carro -> {
                    linhaBD.delete(carro);
                    return carro;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Linha não encontrada"));
    }

    @ApiOperation("Cadastra uma linha")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Necessário ajustes no corpo da requisição.")
    })
    @PostMapping()
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public Linha cadastro(@RequestBody Linha novaLinha){
        linhaBD.save(novaLinha);
        novaLinha.setId(linhaBD.lastId());

        return novaLinha;

    }

}
