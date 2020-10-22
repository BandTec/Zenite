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
import orion.zenite.repositorios.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Api(description = "Operações relacionadas aos horarios do Cronograma", tags = "cronogramaHorarios")
@RestController
@RequestMapping("/api/horarios")
public class CronogramaHorariosController {

    @Autowired
    private CronogramaHorariosRepository repository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private LinhaRepository linhaRepository;

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @ApiOperation("Busca horarios do cronograma pelo ID do cronograma PAI")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/cronograma/{id}")
    public ResponseEntity consultaPorIdCronograma(@PathVariable("id") Integer id) {
        Optional<Cronograma> cronograma = cronogramaRepository.findById(id);
        if (cronograma.isPresent()) {
            List<CronogramaHorarios> listaCronograma = repository.findByCronograma(cronograma.get());
            if (!listaCronograma.isEmpty()) {
                return ok(listaCronograma);
            }
        }
        return notFound().build();
    }

    //Não sei ainda se será usado mas deixo a rota pronta já
    @ApiOperation("Busca horarios do cronograma pelo ID do motorista")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/motorista/{id}")
    public ResponseEntity consultaPorIdMotorista(@PathVariable("id") Integer id) {
        Optional<Motorista> motorista = motoristaRepository.findById(id);
        if(motorista.isPresent()){
            List<CronogramaHorarios> listaCronograma = repository.findByMotorista(motorista.get());
            if (!listaCronograma.isEmpty()) {
                return ok(listaCronograma);
            }
        }

        return notFound().build();
    }

    //Altera um horario para os casos que tenham que alterado motoristas, carros e/ou linhas
    @ApiOperation("Altera um horario de cronograma por completo")
    @PutMapping("/completo/{id}")
    @Transactional
    public ResponseEntity alterar(@RequestBody CronogramaHorarios novoHorario,
                                  @PathVariable Integer id) {
        if (this.repository.existsById(id)) {
            novoHorario.setIdCronogramaHorarios(id);
            // Encriptar senha
            Motorista motorista = novoHorario.getMotorista();
            Carro carro = novoHorario.getCarro();
            Linha linha = novoHorario.getLinha();

            novoHorario.setMotorista(motorista);
            novoHorario.setCarro(carro);
            novoHorario.setLinha(linha);

            // alterar um horario por completo
            this.repository.save(novoHorario);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    //Altera somente o status da viagem
    @ApiOperation("Altera um horario de cronograma por completo")
    @PutMapping("/status/{id}")
    @Transactional
    public ResponseEntity alterarStatusViagem(@RequestBody CronogramaHorarios novoStatus,
                                              @PathVariable Integer id) {
        if (this.repository.existsById(id)) {
            // alterar um horario por completo
            this.repository.save(novoStatus);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @ApiOperation("Cadastra um horario no cronograma")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Necessário ajustes no corpo da requisição.")
    })
    @PostMapping()
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity cadastro(@RequestBody CronogramaHorarios novoHorario) {
        Motorista motorista = novoHorario.getMotorista();
        Carro carro = novoHorario.getCarro();
        Linha linha = novoHorario.getLinha();
        Cronograma cronograma = novoHorario.pegarCronograma();

        novoHorario.setMotorista(motorista);
        novoHorario.setCarro(carro);
        novoHorario.setLinha(linha);
        novoHorario.setCronograma(cronograma);

        repository.save(novoHorario);

        return created(null).build();
    }

}
