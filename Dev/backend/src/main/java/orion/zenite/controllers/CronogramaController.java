package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import orion.zenite.entidades.*;
import orion.zenite.repositorios.CronogramaRepository;
import orion.zenite.repositorios.FiscalRepository;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Api(description = "Operações relacionadas ao Cronograma", tags = "cronograma")
@RestController
@RequestMapping("/api/cronograma")
public class CronogramaController {

    @Autowired
    private CronogramaRepository repository;

    @Autowired
    private FiscalRepository fiscalRepository;

    @ApiOperation("Busca cronograma pelo ID do fiscal")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/fiscal/{id}")
    public ResponseEntity consultaPorFiscal(@PathVariable("id") Integer id){
        Fiscal f = new Fiscal();
        f.setId(id);
        Optional<Fiscal> listaCronograma = fiscalRepository.findById(f.getId());
        if(!listaCronograma.isPresent()){
            return ok(listaCronograma);
        }
        return notFound().build();
    }

    //Não coloquei o update por imaginar não ser necessário, fazer o update de um registro pai de cronograma

    @ApiOperation("Deleta um cronograma por seu ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Linha não encontrado.")
    })
    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") Integer id){
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    //Primeiro passo de cadastro de um cronograma, é cadastrar cronograma PAI
    @ApiOperation("Cadastra um cronograma")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Necessário ajustes no corpo da requisição.")
    })
    @PostMapping()
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity cadastro(@RequestBody Cronograma cronograma){
        Fiscal fiscal = cronograma.getFiscal();

        cronograma.setFiscal(fiscal);

        repository.save(cronograma);

        return created(null).build();
    }

}
