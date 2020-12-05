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
import orion.zenite.repositorios.LinhaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Api(description = "Operações relacionadas aos horarios alterados do Cronograma", tags = "intervalorDeTempo")
@RestController
@RequestMapping("/api/horarios/alterados")
public class CronogramaHorariosAlteradosController {

    @Autowired
    private CronogramaHorariosAlteradosRepository repository;

    @Autowired
    private LinhaRepository linhaRepository;

    @Autowired
    private CronogramaHorariosRepository cronogramaHorariosRepository;

    @ApiOperation("Busca horarios do cronograma pelo ID do horario cronograma PAI")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/cronograma/{id}")
    public ResponseEntity consultaPorIdCronograma(@PathVariable("id") Integer id) {
        CronogramaHorarios ch = new CronogramaHorarios();
        ch.setIdCronogramaHorarios(id);
        Optional<CronogramaHorariosAlterados> listaCronograma = repository.findById(ch.getIdCronogramaHorarios());
        if (!listaCronograma.isPresent()) {
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
    @PostMapping("/cronograma/{idLinha}/{novoIntervalo}")
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity cadastro(@PathVariable("idLinha") Integer id, @PathVariable("novoIntervalo") String novo_intervalo) {

        Optional<Linha> linha = linhaRepository.findById(id);
        System.out.println(linha);
        System.out.println(id);
        if (linha.isPresent()) {
            CronogramaHorarios cronogramaHorarios = new CronogramaHorarios();
            List<CronogramaHorarios> ch = cronogramaHorariosRepository.findAllByLinha(linha.get());
            System.out.println(ch);
            if (!ch.isEmpty()) {
                for (CronogramaHorarios ch1 : ch) {

                    if (ch1.getViagemStatus() == 3)
                        continue;

                    CronogramaHorariosAlterados cha = new CronogramaHorariosAlterados();
                    LocalDateTime novaDataChegada = ch1.getHoraPrevistaChegada();
                    novaDataChegada = novaDataChegada.plusMinutes(Integer.parseInt(novo_intervalo));

                    LocalDateTime novaDataSaida = ch1.getHoraPrevistaSaida();
                    novaDataSaida = novaDataSaida.plusMinutes(Integer.parseInt(novo_intervalo));


                    cha.setCronogramaHorarios(ch1);
                    cha.setNovaHoraPrevistaChegada(novaDataChegada);
                    cha.setNovaHoraPrevistaSaida(novaDataSaida);

                    repository.save(cha);
                }
            }

            return created(null).build();
        } else {
            return notFound().build();
        }
    }

}
