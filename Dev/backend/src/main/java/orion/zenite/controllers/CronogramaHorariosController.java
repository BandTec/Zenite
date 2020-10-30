package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import orion.zenite.entidades.*;
import orion.zenite.modelos.ConsultaPaginada;
import orion.zenite.repositorios.*;

import java.util.List;
import java.util.Optional;

import orion.zenite.modelos.*;

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
    private ViagemRepository viagemRepository;

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @ApiOperation("Busca horarios do cronograma pelo ID do cronograma PAI")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/cronograma/{id}")
    public ResponseEntity consultaPorIdCronograma(@PathVariable("id") Integer id, @RequestParam(required = false) Integer pagina) {
        if (this.repository.count() > 0) {
            if (pagina != null) {
                Optional<Cronograma> cronograma = cronogramaRepository.findById(id);
                if (cronograma.isPresent()) {
                    Pageable pageable = PageRequest.of(pagina, 10);
                    Page<CronogramaHorarios> listaCronograma = repository.findByCronograma(cronograma.get(), pageable);
                    ConsultaPaginada consulta = new ConsultaPaginada(listaCronograma);
                    return ok(consulta);
                }
            } else {

                Optional<Cronograma> cronograma = cronogramaRepository.findById(id);
                if (cronograma.isPresent()) {
                    List<CronogramaHorarios> listaCronograma = repository.findByCronograma(cronograma.get());
                    return ok(listaCronograma);
                }
            }
        } else {
            return noContent().build();
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
        if (motorista.isPresent()) {
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

    @ApiOperation("Buscar horarios por id linha ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 204, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/linha/{id}")
    public ResponseEntity consultarPorLinha(@PathVariable("id") Integer id) {
        // Optional<Linha> linha = linhaRepository.findById(id);

        LocalDate date = LocalDate.now();
        List<CronogramaHorarios> horariosLinha = repository.getViagensDoDiaPorLinha(id, date);

        List<Viagem> viagensFeitas = viagemRepository.getViagensDaLinhaDoDia(date, id);
        if (!horariosLinha.isEmpty()) {
            ArrayList<CronogramaLinha> lista = new ArrayList<>();

            for (int i = 0; i < horariosLinha.size(); i++) {
                CronogramaHorarios item = horariosLinha.get(i);
                CronogramaLinha cronograma = new CronogramaLinha();

                cronograma.setAtrasado(false);

                // ANTIGO SERIA O HORARIO NA TABELA CRONOGRAMA HORARIO SE HOUVER UM NOVO HORARIO PREVISTO
                cronograma.setHorarioAntigo("");

                DateTimeFormatter sdf = DateTimeFormatter.ofPattern("HH:mm");
                cronograma.setHorarioPrevisto(item.getHoraPrevistaSaida().format(sdf) + " - " + item.getHoraPrevistaChegada().format(sdf));
                cronograma.setNomeMotorista(item.getMotorista().getNomeFormatado());

                cronograma.setHorarioRealizado("");
                if (!viagensFeitas.isEmpty()) {
                    if (i < viagensFeitas.size()) {
                        Viagem v = viagensFeitas.get(i);
                        if(v.getHoraSaida() != null){
                            if(v.getHoraSaida().isEqual(item.getHoraPrevistaSaida()) || v.getHoraSaida().isAfter(item.getHoraPrevistaSaida())){
                                cronograma.setHorarioRealizado("em viagem");
                            }
                        }
                        if (v.getHoraChegada() != null) {
                            String realizado = v.getHoraSaida().format(sdf) + " - " + v.getHoraChegada().format(sdf);
                            cronograma.setHorarioRealizado(realizado);

                            // checa se motorista esta atrasado
                            cronograma.setAtrasado(v.getHoraChegada().isAfter(item.getHoraPrevistaChegada()));
                        }
                    }
                }
                lista.add(cronograma);
            }
            ;

            return ok(lista);
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
    public ResponseEntity consultarViagensDaProximaHora(@PathVariable("id") Integer id) {
        LocalDateTime dataHoraSPInicio = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/Sao_Paulo"));
        LocalDateTime dataHoraSPFim = dataHoraSPInicio.plusHours(1);
        LocalDate date = LocalDate.now();
        System.out.println(dataHoraSPInicio);
        List<CronogramaHorarios> cronogramaHorarios = repository.getViagensProximaHora(id, dataHoraSPInicio, dataHoraSPFim, date);
        List<Viagem> viagensFeitas = viagemRepository.getViagensDoFiscalDoDia(dataHoraSPInicio, id);
        if (!cronogramaHorarios.isEmpty()) {
            ArrayList<CronogramaFiscal> listaViagens = new ArrayList<CronogramaFiscal>();
            ArrayList<Integer> linhas = new ArrayList<>();

            for (int i = 0; i < cronogramaHorarios.size(); i++) {
                CronogramaHorarios item = cronogramaHorarios.get(i);
                int linhaAtual = item.getLinha().getId();

                if (i > 0) {
                    int anterior = i - 1;
                    if (cronogramaHorarios.get(anterior).getLinha().getId() != linhaAtual) {
                        linhas.add(linhaAtual);
                    }
                } else {
                    linhas.add(linhaAtual);
                }
            }


            linhas.forEach(linhaId -> {
                CronogramaFiscal fiscal = new CronogramaFiscal();
                ArrayList<CronogramaLinha> lista = new ArrayList<>();

                for (int i = 0; i < cronogramaHorarios.size(); i++) {
                    CronogramaHorarios item = cronogramaHorarios.get(i);
                    if (linhaId == item.getLinha().getId()) {
                        CronogramaLinha cronograma = new CronogramaLinha();

                        cronograma.setAtrasado(false);

                        // ANTIGO SERIA O HORARIO NA TABELA CRONOGRAMA HORARIO SE HOUVER UM NOVO HORARIO PREVISTO
                        cronograma.setHorarioAntigo("");

                        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("HH:mm");
                        cronograma.setHorarioPrevisto(item.getHoraPrevistaSaida().format(sdf) + " - " + item.getHoraPrevistaChegada().format(sdf));
                        cronograma.setNomeMotorista(item.getMotorista().getNomeFormatado());

                        cronograma.setHorarioRealizado("");
                        if (!viagensFeitas.isEmpty()) {
                            if (i < viagensFeitas.size()) {
                                Viagem v = viagensFeitas.get(i);
                                if(v.getHoraSaida() != null){
                                    if(v.getHoraSaida().isEqual(item.getHoraPrevistaSaida()) || v.getHoraSaida().isAfter(item.getHoraPrevistaSaida())){
                                        cronograma.setHorarioRealizado("em viagem");
                                    }
                                }
                                if (linhaId == v.getLinha().getId()) {
                                    if (v.getHoraChegada() != null) {
                                        String realizado = v.getHoraSaida().format(sdf) + " - " + v.getHoraChegada().format(sdf);
                                        cronograma.setHorarioRealizado(realizado);

                                        // checa se motorista esta atrasado
                                        cronograma.setAtrasado(v.getHoraChegada().isAfter(item.getHoraPrevistaChegada()));
                                    }
                                }
                            }
                        }

                        lista.add(cronograma);
                        fiscal.setNomeLinha(item.getLinha().getNumero());
                        fiscal.setIdLinha(item.getLinha().getId());
                    }
                }
                ;
                fiscal.setCronograma(lista);
                listaViagens.add(fiscal);
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
    public ResponseEntity consultarViagemAtualOuProxima(@PathVariable("id") Integer id) {
        LocalDateTime dataHoraSP = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/Sao_Paulo"));
        Optional<CronogramaHorarios> cronogramaHorarios = repository.findActualOrNextViagem(id, dataHoraSP);
        if (cronogramaHorarios.isPresent()) {

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
    public ResponseEntity consultarViagensDia(@PathVariable("id") Integer id) {
        LocalDate dataSP = LocalDate.now();
        int viagensRealizadas = repository.getViagensRealizadas(id, dataSP);
        int viagensRestantes = repository.getViagensRestantes(id, dataSP);
        Optional<List<CronogramaHorarios>> viagensDia = repository.getViagensDoDia(id, dataSP);

        if (viagensDia.isPresent()) {
            List<CronogramaHorarios> lista = viagensDia.get();
            ArrayList<ViagemMotorista> listaViagens = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            lista.forEach(item -> {
                ViagemMotorista nova = new ViagemMotorista();
                long duracaoMin = item.getHoraPrevistaSaida().until(item.getHoraPrevistaChegada(), ChronoUnit.MINUTES);
                nova.setDuracao(String.format("%d MIN", duracaoMin));
                nova.setHorario(String.format("%s-%s", item.getHoraPrevistaSaida().format(formatter), item.getHoraPrevistaChegada().format(formatter)));
                listaViagens.add(nova);
            });

            ViagemDiaria viagemDiaria = new ViagemDiaria();
            viagemDiaria.setViagensRealizadas(viagensRealizadas);
            viagemDiaria.setViagensRestantes(viagensRestantes);
            viagemDiaria.setListaViagens(listaViagens);
            return ok().body(viagemDiaria);
        }
        return notFound().build();
    }

}
