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
import orion.zenite.modelos.CronogramaFiscal;
import orion.zenite.modelos.CronogramaHorarioSimples;
import orion.zenite.modelos.ViagemMotorista;
import orion.zenite.modelos.Viagens;
import orion.zenite.repositorios.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
    public ResponseEntity consultaPorIdCronograma(@PathVariable("id") Integer id){
        Cronograma c = new Cronograma();
        c.setIdCronograma(id);
        Optional<Cronograma> listaCronograma = cronogramaRepository.findById(c.getIdCronograma());
        if(!listaCronograma.isPresent()){
            return ok(listaCronograma);
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
    public ResponseEntity consultaPorIdMotorista(@PathVariable("id") Integer id){
        Motorista m = new Motorista();
        m.setId(id);
        Optional<Motorista> listaCronograma = motoristaRepository.findById(m.getId());
        if(!listaCronograma.isPresent()){
            return ok(listaCronograma);
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
    public ResponseEntity cadastro(@RequestBody CronogramaHorarios novoHorario){
        Motorista motorista = novoHorario.getMotorista();
        Carro carro = novoHorario.getCarro();
        Linha linha = novoHorario.getLinha();
        Cronograma cronograma = novoHorario.getCronograma();

        novoHorario.setMotorista(motorista);
        novoHorario.setCarro(carro);
        novoHorario.setLinha(linha);
        novoHorario.setCronograma(cronograma);

        repository.save(novoHorario);

        return created(null).build();
    }

    @ApiOperation("Buscar horarios por id linha ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 204, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/linha/{id}")
    public ResponseEntity consultarPorLinha(@PathVariable("id") Integer id){
        Optional<Linha> linha = linhaRepository.findById(id);
        List<CronogramaHorarios> horariosLinha = repository.findByLinha(linha.get());
        if(!horariosLinha.isEmpty()){
            return ok(horariosLinha);
        }
        return noContent().build();
    }

    @ApiOperation("Buscar os horários da proxima hora de todas as linhas do fiscal ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 204, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/fiscal/{id}/cronograma/proximahora")
    public ResponseEntity consultarViagensDaProximaHora(@PathVariable("id") Integer id){
        LocalDateTime dataHoraSPInicio = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/Sao_Paulo"));
        LocalDateTime dataHoraSPFim = dataHoraSPInicio.plusHours(1);
        List<CronogramaHorarios> cronogramaHorarios = repository.getViagensProximaHora(id, dataHoraSPInicio, dataHoraSPFim);
        if(!cronogramaHorarios.isEmpty()){
            ArrayList<CronogramaFiscal> listaViagens = new ArrayList<CronogramaFiscal>();

            cronogramaHorarios.forEach(item -> {
                CronogramaFiscal nova = new CronogramaFiscal();
                nova.setChegada(item.getHoraPrevistaChegada().toString());
                nova.setSaida(item.getHoraPrevistaSaida().toString());
                nova.setAtrasado(false);
                nova.setNomeMotorista(item.getMotorista().getNome());
                listaViagens.add(nova);
            });

            return ok().body(listaViagens);
        }
        return noContent().build();
    }

    @ApiOperation("Buscar a viagem atual ou a proxima do motorista")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/motorista/{id}/viagem/atual")
    public ResponseEntity consultarViagemAtualOuProxima(@PathVariable("id") Integer id){
        LocalDateTime dataHoraSP = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/Sao_Paulo"));
        Optional<CronogramaHorarios> cronogramaHorarios = repository.findActualOrNextViagem(id, dataHoraSP);
        if(cronogramaHorarios.isPresent()){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            long duracaoMin = cronogramaHorarios.get().getHoraPrevistaSaida().until(cronogramaHorarios.get().getHoraPrevistaChegada(), ChronoUnit.MINUTES);
            String duracao = String.format("%d MINUTOS", duracaoMin);

            ViagemMotorista viagemMotorista = new ViagemMotorista();
            viagemMotorista.setDuracao(duracao);
            viagemMotorista.setHorario(cronogramaHorarios.get().getHoraPrevistaSaida().format(formatter));

            return ok(viagemMotorista);
        }
        return notFound().build();
    }

    @ApiOperation("Buscar sumário de viagens do dia e viagens do dia do motorista")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/motorista/{id}/viagem/dia")
    public ResponseEntity consultarViagensDia(@PathVariable("id") Integer id){
        LocalDate dataSP = LocalDate.now();
        int viagensRealizadas = repository.getViagensRealizadas(id, dataSP);
        int viagensRestantes = repository.getViagensRestantes(id, dataSP);
        Optional<List<CronogramaHorarios>> viagensDia = repository.getViagensDoDia(id, dataSP);

        if(viagensDia.isPresent()){
            List<CronogramaHorarios> lista = viagensDia.get();
            ArrayList<Viagens> listaViagens = new ArrayList<Viagens>();

            lista.forEach(item -> {
                Viagens nova = new Viagens();
                nova.setChegada(item.getHoraPrevistaChegada().toString());
                nova.setSaida(item.getHoraPrevistaSaida().toString());
                listaViagens.add(nova);
            });

            CronogramaHorarioSimples horarioSimples = new CronogramaHorarioSimples();
            horarioSimples.setData(lista.get(0).getCronograma().getDataCronograma().toString());
            horarioSimples.setViagens(listaViagens);

            return ok().body(horarioSimples);
        }
        return notFound().build();
    }

}
