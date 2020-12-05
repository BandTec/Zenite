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
import orion.zenite.modelos.*;
import orion.zenite.entidades.*;
import orion.zenite.repositorios.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Api(description = "Operações relacionadas a viagem", tags = "Viagem")
@RestController
@RequestMapping("/api/viagem")
public class ViagemController {

    @Autowired
    private ViagemRepository repository;

    @Autowired
    private FiscalRepository fiscalRepository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private LinhaRepository linhaRepository;

    @Autowired
    private CronogramaHorariosRepository cronogramaHorariosRepository;

    @ApiOperation("Lista todos as viagens")
    @GetMapping
    public ResponseEntity consulta(@RequestParam(required = false) Integer pagina,
                                   @RequestParam(required = false) String q
    ) {
        if (this.repository.count() > 0) {
            if (pagina != null) {
                Pageable pageable = PageRequest.of(pagina, 10);
                Page<Viagem> page = repository.findAll(pageable);
                ConsultaPaginada consulta = new ConsultaPaginada(page);
                return ok(consulta);
            } else {
                List<Viagem> consulta = repository.findAll();
                return ok(consulta);
            }
        } else {
            return noContent().build();
        }
    }

    @ApiOperation("Exclui uma viagem por sua id")
    @DeleteMapping("/{id}")
    public ResponseEntity excluirViagem(@PathVariable("id") Integer id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @ApiOperation("Exibe viagem por sua id")
    @GetMapping("{id}")
    public ResponseEntity consultar(@PathVariable("id") Integer id) {
        Optional<Viagem> consultaViagem = this.repository.findById(id);

        if (consultaViagem.isPresent()) {
            return ok(consultaViagem);
        } else {
            return notFound().build();
        }

    }

    @ApiOperation("Exibe viagens de uma linha")
    @GetMapping("/linha/{id}")
    public ResponseEntity consultarPorLinha(@PathVariable("id") Integer id) {
        Optional<Linha> linha = linhaRepository.findById(id);
        if (linha.isPresent()) {
            List<Viagem> consultaViagem = this.repository.findByLinha(linha.get());
            if (!consultaViagem.isEmpty()) {
                return ok(consultaViagem);
            }
        }

        return notFound().build();
    }

    @ApiOperation("Exibe viagens de um ônibus")
    @GetMapping("/onibus/{id}")
    public ResponseEntity consultarPorOnibus(@PathVariable("id") Integer id) {
        Optional<Carro> carro = carroRepository.findById(id);
        if (carro.isPresent()) {
            List<Viagem> consultaViagem = this.repository.findByCarro(carro.get());
            if (!consultaViagem.isEmpty()) {
                return ok(consultaViagem);
            }
        }

        return notFound().build();
    }

    @ApiOperation("Exibe viagens de um motorista")
    @GetMapping("/motorista/{id}")
    public ResponseEntity consultarPorMotorista(@PathVariable("id") Integer id,
                                                @RequestParam(required = false) Integer pagina,
                                                @RequestParam(required = false) String q) {
        Optional<Motorista> motorista = motoristaRepository.findById(id);
        if (motorista.isPresent()) {
            if (this.repository.count() > 0) {
                if (pagina != null) {
                    Pageable pageable = PageRequest.of(pagina, 10);
                    Page<Viagem> page = repository.findByMotorista(pageable, motorista.get());
                    ConsultaPaginada consulta = new ConsultaPaginada(page);
                    return ok(consulta);
                } else {
                    List<Viagem> consultaViagem = this.repository.findByMotorista(motorista.get());
                    if (!consultaViagem.isEmpty()) {
                        return ok(consultaViagem);
                    }
                }
            } else {
                return noContent().build();
            }
        }

        return notFound().build();
    }

//    @ApiOperation("Altera uma viagem")
//    @PutMapping("{id}")
//    public ResponseEntity alterar(@RequestBody ViagemDto viagem,
//                                  @PathVariable Integer id) {
//        viagem.setViagemId(id);
//        Viagem novaViagem = montaViagem(viagem);
//        novaViagem.setId(viagem.getViagemId());
//
//        Optional<Viagem> v = repository.findById(viagem.getViagemId());
//        if (novaViagem == null || !v.isPresent()) {
//            return badRequest().build();
//        } else {
//            this.repository.save(novaViagem);
//            return ok().build();
//        }
//    }

    @ApiOperation("Altera uma viagem")
    @PutMapping("{id}/qtdPassageiros")
    public ResponseEntity addQuantidadePassageiros(
            @RequestBody ViagemPassageiros passageiros,
            @PathVariable Integer id
    ) {
        Optional<Viagem> consultaViagem = this.repository.findById(id);

        if (consultaViagem == null || !consultaViagem.isPresent()) {
            return badRequest().build();
        } else {
            Viagem viagem = consultaViagem.get();
            viagem.setQtdPassageiros(passageiros.getQtdPassageiros());
            this.repository.save(viagem);
            return ok().build();
        }
    }

    //    @ApiOperation("Cadastra uma viagem")
//    @PostMapping
//    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
//    public ResponseEntity cadastrar(@RequestBody ViagemDto viagem) {
//        Viagem novaViagem = montaViagem(viagem);
//
//        if (novaViagem == null) {
//            return badRequest().build();
//        } else {
//            this.repository.save(novaViagem);
//            return created(null).build();
//        }
//    }
    @ApiOperation("Buscar sumário de todas as viagens do motorista")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/app/motorista/{id}")
    public ResponseEntity consultarViagensMotorista(@PathVariable("id") Integer id) {
        Optional<Motorista> motorista = motoristaRepository.findById(id);
        if (motorista.isPresent()) {
            List<Viagem> viagens = repository.findByMotorista(motorista.get());

            if (!viagens.isEmpty()) {
                ArrayList<ViagensMotorista> viagensDia = new ArrayList<>();
                DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
                DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                ArrayList<ViagemMotorista> listaViagens = new ArrayList<>();

                for (int i = 0; i < viagens.size(); i++) {
                    Viagem atual = viagens.get(i);

                    ViagemMotorista nova = new ViagemMotorista();
                    long duracaoMin;
                    if(atual.getHoraChegada() != null){
                        duracaoMin = atual.getHoraSaida().until(
                                atual.getHoraChegada(), ChronoUnit.MINUTES);
                        nova.setHorario(String.format("%s-%s",
                                atual.getHoraSaida().format(formatterTime),
                                atual.getHoraChegada().format(formatterTime)));
                    }else {
                        duracaoMin = 0;
                        nova.setHorario(String.format("%s-%s",
                                atual.getHoraSaida().format(formatterTime),
                                " "));
                    }
                    nova.setDuracao(String.format("%d MIN", duracaoMin));


                    if (i != 0) {
                        // se for diferente da primeira viagem
                        // verifica se viagem anterior é no
                        //  mesmo dia da viagem atual
                        // se sim adicionar a lista de viagens
                        int anterior = i - 1;
                        LocalDate viagemAnterior = viagens.get(anterior).getHoraSaida().toLocalDate();
                        LocalDate viagemAtual = atual.getHoraSaida().toLocalDate();

                        if (viagemAnterior.isEqual(viagemAtual)) {
                            listaViagens.add(nova);
                            if(i == viagens.size() - 1){
                                ViagensMotorista dia = new ViagensMotorista();
                                dia.setData(viagemAnterior.format(formatterDate));
                                dia.setViagens(listaViagens);
                                viagensDia.add(dia);
                            }
                        } else {
                            // salva viagem no dia

                            ViagensMotorista dia = new ViagensMotorista();
                            dia.setData(viagemAnterior.format(formatterDate));
                            dia.setViagens(listaViagens);
                            viagensDia.add(dia);

                            // e limpa lista de viagens e adicionar viagem atual

                            listaViagens = new ArrayList<>();
                            listaViagens.add(nova);

                            if(i == viagens.size() - 1){
                                ViagensMotorista diaUltimo = new ViagensMotorista();
                                diaUltimo.setData(atual.getHoraSaida().format(formatterDate));
                                diaUltimo.setViagens(listaViagens);
                                viagensDia.add(diaUltimo);
                            }
                        }
                    } else {
                        // se for a primeira viagem ja adiciona na lista
                        listaViagens.add(nova);
                    }

                }

                MotoViagens mv = new MotoViagens();
                mv.setViagens(viagensDia);
                mv.setQtd(viagens.size());
                return ok().body(mv);
            }
        }
        return notFound().build();
    }


    @ApiOperation("Finaliza uma viagem")
    @PutMapping("{id}/{fiscalId}")
    public ResponseEntity finalizarViagem(@PathVariable Integer id, @PathVariable Integer fiscalId) {
        Optional<Fiscal> f = fiscalRepository.findById(fiscalId);

        Optional<Viagem> v = repository.findById(id);
        if (!v.isPresent() & !f.isPresent()) {
            return badRequest().build();
        } else {
            Fiscal fiscalVolta = f.get();
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());

            Viagem viagem = v.get();
            viagem.setHoraChegada(timestamp.toLocalDateTime());
            viagem.setFiscalVolta(fiscalVolta);
            this.repository.save(viagem);

            LocalDateTime dataHoraSPInicio = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/Sao_Paulo"));

            List<CronogramaHorarios> cronos = cronogramaHorariosRepository.procurarViagensIniciadas(viagem.getLinha().getId(), dataHoraSPInicio);
            System.out.println(cronos.size());

            CronogramaHorarios alterar = null;
            if(!cronos.isEmpty()){
                for (CronogramaHorarios crono : cronos) {
                    Boolean horaCerta = crono.getHoraPrevistaSaida().isEqual(dataHoraSPInicio);
                    Boolean depois = crono.getHoraPrevistaSaida().isAfter(dataHoraSPInicio);

                    if(crono.getMotorista().getId() == viagem.getMotorista().getId()) {
//                        System.out.println(crono.getHoraPrevistaChegada());
//                        System.out.println(dataHoraSPInicio);
//                        System.out.println(horaCerta);
//                        System.out.println(depois);

                        if (horaCerta || depois) {
//                            System.out.println("achei");
                            crono.setViagemStatus(3);
                            alterar = crono;
                            break;
                        }
                    }
                }
            }
            if(alterar != null){
                cronogramaHorariosRepository.save(alterar);
            }

            return ok().build();
        }
    }

    @ApiOperation("Inicia uma viagem")
    @PostMapping
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity iniciarViagem(@RequestBody ViagemIniciar viagem) {
        Viagem novaViagem = montaViagem(viagem);

        if (novaViagem == null) {
            return badRequest().build();
        } else {

            this.repository.save(novaViagem);

            LocalDateTime dataHoraSPInicio = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/Sao_Paulo"));

            List<CronogramaHorarios> cronos = cronogramaHorariosRepository.procurarViagensNaoIniciadas(novaViagem.getLinha().getId(), dataHoraSPInicio);
            System.out.println(cronos.size());

            CronogramaHorarios alterar = null;
            if(!cronos.isEmpty()){
                for (CronogramaHorarios crono : cronos) {
                    Boolean horaCerta = crono.getHoraPrevistaSaida().isEqual(dataHoraSPInicio);
                    Boolean depois = crono.getHoraPrevistaSaida().isAfter(dataHoraSPInicio);

                    if(crono.getMotorista().getId() == novaViagem.getMotorista().getId()) {
//                        System.out.println(crono.getHoraPrevistaChegada());
//                        System.out.println(dataHoraSPInicio);
//                        System.out.println(horaCerta);
//                        System.out.println(depois);

                        if (horaCerta || depois) {
//                            System.out.println("achei");
                            crono.setViagemStatus(2);
                            alterar = crono;
                            break;
                        }
                    }
                }
            }
            if(alterar != null){
                cronogramaHorariosRepository.save(alterar);
            }

            return created(null).build();
        }
    }

    public Viagem montaViagem(ViagemIniciar viagem) {
        Viagem novaViagem = new Viagem();

        Optional<Fiscal> f = fiscalRepository.findById(viagem.getFiscalId());
        Optional<Motorista> m = motoristaRepository.findById(viagem.getMotoristaId());

        if (m.isPresent() & f.isPresent()) {
            Fiscal fiscal = f.get();
            novaViagem.setFiscal(fiscal);

            Motorista motorista = m.get();
            novaViagem.setMotorista(motorista);

            novaViagem.setCarro(motorista.getCarro());
            novaViagem.setLinha(motorista.getCarro().pegarLinha());

            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            novaViagem.setHoraSaida(timestamp.toLocalDateTime());
            novaViagem.setQtdPassageiros(0);

            return novaViagem;
        } else {
            return null;
        }

    }

//    public Viagem montaViagem(ViagemDto viagem) {
//        Viagem novaViagem = new Viagem();
//
//        Optional<Fiscal> f = fiscalRepository.findById(viagem.getFiscalId());
//        Fiscal fiscal = f.orElse(null);
//        novaViagem.setFiscal(fiscal);
//
//        Optional<Linha> l = linhaRepository.findById(viagem.getLinhaId());
//        Linha linha = l.orElse(null);
//        novaViagem.setLinha(linha);
//
//        Optional<Motorista> m = motoristaRepository.findById(viagem.getMotoristaId());
//        Motorista motorista = m.orElse(null);
//        novaViagem.setMotorista(motorista);
//
//        Optional<Carro> c = carroRepository.findById(viagem.getCarroId());
//        Carro carro = c.orElse(null);
//        novaViagem.setCarro(carro);
//
//        novaViagem.setHoraChegada(viagem.getHoraChegada());
//        novaViagem.setHoraSaida(viagem.getHoraSaida());
//        novaViagem.setQtdPassageiros(viagem.getQtdPassageiros());
//
//        if (fiscal == null || linha == null || carro == null || motorista == null) {
//            return null;
//        } else {
//            return novaViagem;
//        }
//    }

}
