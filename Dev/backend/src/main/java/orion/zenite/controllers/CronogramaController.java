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
import orion.zenite.modelos.CronogramaLista;
import orion.zenite.modelos.Horarios;
import orion.zenite.repositorios.*;

import java.util.List;
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

    @Autowired
    private LinhaRepository linhaRepository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private CronogramaHorariosRepository cronogramaHorariosRepository;

    @ApiOperation("Busca cronograma pelo ID do fiscal")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/fiscal/{id}")
    public ResponseEntity consultaPorFiscal(@PathVariable("id") Integer id) {
        Optional<Fiscal> fiscal = fiscalRepository.findById(id);

        if (fiscal.isPresent()) {
            List<Cronograma> listaCronograma = repository.findByFiscal(fiscal.get());
            if (listaCronograma.isEmpty()) {
                return notFound().build();
            } else {
                return ok(listaCronograma);
            }
        }
        return notFound().build();
    }

    @ApiOperation("Busca cronograma todos os cronogramas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping
    public ResponseEntity consultarTodos(@RequestParam(required = false) Integer pagina) {
        if (this.repository.count() > 0) {
            if(pagina != null) {
                Pageable pageable = PageRequest.of(pagina, 10);
                Page<Cronograma> page = repository.findAll(pageable);
                ConsultaPaginada consulta = new ConsultaPaginada(page);
                return ok(consulta);
            }
            else {
                List<Cronograma> listaCronograma = repository.findAll();
                return ok(listaCronograma);
            }
        } else {
            return noContent().build();
        }
    }


    //Não coloquei o update por imaginar não ser necessário, fazer o update de um registro pai de cronograma

    @ApiOperation("Deleta um cronograma por seu ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Linha não encontrado.")
    })
    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") Integer id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    //Primeiro passo de cadastro de um cronograma, é cadastrar cronograma PAI
//    @ApiOperation("Cadastra um cronograma")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
//            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
//            @ApiResponse(code = 404, message = "Necessário ajustes no corpo da requisição.")
//    })
//    @PostMapping()
//    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
//    public ResponseEntity cadastro(@RequestBody Cronograma cronograma){
//        Fiscal fiscal = cronograma.getFiscal();
//
//        cronograma.setFiscal(fiscal);
//
//        repository.save(cronograma);
//
//        return created(null).build();
//    }

    //Primeiro passo de cadastro de um cronograma, é cadastrar cronograma PAI
    @ApiOperation("Cadastra um cronograma")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Necessário ajustes no corpo da requisição.")
    })
    @PostMapping()
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity cadastroCronogramaLista(@RequestBody CronogramaLista cronogramaNovo) {

        Optional<Linha> linha = linhaRepository.findById(cronogramaNovo.getLinhaId());
        if (linha.isPresent()) {
            Fiscal fiscal = linha.get().pegarFiscal();
            Cronograma cronograma = new Cronograma();
            cronograma.setFiscal(fiscal);
            cronograma.setDataCronograma(cronogramaNovo.getDataCronograma());
            repository.save(cronograma);

            Cronograma cronogramaSalvo = repository.findById(repository.lastId()).get();
            List<Horarios> horariosList = cronogramaNovo.getHorarios();
            horariosList.forEach(horarios -> {

                Motorista motorista = motoristaRepository.findById(horarios.getMotoristaId()).get();
                Carro carro = motorista.getCarro();

                CronogramaHorarios novoHorario = new CronogramaHorarios();
                novoHorario.setMotorista(motorista);
                novoHorario.setCarro(carro);
                novoHorario.setViagemStatus(1);
                novoHorario.setLinha(linha.get());
                novoHorario.setCronograma(cronograma);
                novoHorario.setHoraPrevistaChegada(horarios.getHoraPrevistaChegada());
                novoHorario.setHoraPrevistaSaida(horarios.getHoraPrevistaSaida());
                cronogramaHorariosRepository.save(novoHorario);
            });
            return created(null).build();
        } else {
            return badRequest().build();
        }
    }

}
