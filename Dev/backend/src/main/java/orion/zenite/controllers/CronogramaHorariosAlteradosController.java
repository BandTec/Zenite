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
import orion.zenite.repositorios.CronogramaHorariosAlteradosRepository;
import orion.zenite.repositorios.CronogramaHorariosRepository;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Api(description = "Operações relacionadas aos horarios alterados do Cronograma", tags = "intervalorDeTempo")
@RestController
@RequestMapping("/api/horarios/alterados")
public class CronogramaHorariosAlteradosController {

    @Autowired
    private CronogramaHorariosAlteradosRepository repository;

    @Autowired
    private CronogramaHorariosRepository cronogramaHorariosRepository;

    @ApiOperation("Busca horarios do cronograma pelo ID do horario cronograma PAI")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/cronograma/{id}")
    public ResponseEntity consultaPorIdCronograma(@PathVariable("id") Integer id){
        CronogramaHorarios ch = new CronogramaHorarios();
        ch.setIdCronogramaHorarios(id);
        Optional<CronogramaHorariosAlterados> listaCronograma = repository.findById(ch.getIdCronogramaHorarios());
        if(!listaCronograma.isPresent()){
            return ok(listaCronograma);
        }
        return notFound().build();
    }

    @ApiOperation("Cadastra um novo horario para um horario já existente no cronograma")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Necessário ajustes no corpo da requisição.")
    })
    @PostMapping()
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity cadastro(@RequestBody CronogramaHorariosAlterados novoHorarioAlterado){

        CronogramaHorarios ch = novoHorarioAlterado.getCronogramaHorarios();

        novoHorarioAlterado.setCronogramaHorarios(ch);

        repository.save(novoHorarioAlterado);

        return created(null).build();
    }

}
