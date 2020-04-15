package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import orion.zenite.dto.ViagemDto;
import orion.zenite.entidades.*;
import orion.zenite.repositorios.*;

import java.util.Optional;

@Api(description = "Operações relacionadas a viagem", tags = "viagem")
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


    @ApiOperation("Lista todos as viagens")
    @GetMapping
    public ResponseEntity consulta() {

        if (this.repository.count() > 0) {
            return ResponseEntity.ok(this.repository.findAll());
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @ApiOperation("Exibe ônibus por id")
    @GetMapping("{id}")
    public ResponseEntity consultar(@PathVariable("id") Integer id) {
        Optional<Viagem> consultaViagem = this.repository.findById(id);

        if (consultaViagem.isPresent()) {
            return ResponseEntity.ok(consultaViagem);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirViagem(@PathVariable("id") Integer id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @ApiOperation("Altera uma viagem")
    @PutMapping
    public ResponseEntity alterar(@RequestBody ViagemDto viagem) {
        Viagem novaViagem = montaViagem(viagem);
        novaViagem.setId(viagem.getViagemId());

        Optional<Viagem> v = repository.findById(viagem.getViagemId());
        if(novaViagem == null || !v.isPresent()){
            return ResponseEntity.badRequest().build();
        } else {
            this.repository.save(novaViagem);
            return ResponseEntity.ok().build();
        }
    }

    @ApiOperation("Cadastra uma viagem")
    @PostMapping
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity cadastrar(@RequestBody ViagemDto viagem) {
        Viagem novaViagem = montaViagem(viagem);

        if(novaViagem == null){
            return ResponseEntity.badRequest().build();
        } else {
            this.repository.save(novaViagem);
            return ResponseEntity.created(null).build();
        }
    }

    public Viagem montaViagem(ViagemDto viagem) {
        Viagem novaViagem = new Viagem();

        Optional<Fiscal> f = fiscalRepository.findById(viagem.getFiscalId());
        Fiscal fiscal = f.orElse(null);
        novaViagem.setFiscal(fiscal);

        Optional<Linha> l = linhaRepository.findById(viagem.getLinhaId());
        Linha linha = l.orElse(null);
        novaViagem.setLinha(linha);

        Optional<Motorista> m = motoristaRepository.findById(viagem.getMotoristaId());
        Motorista motorista = m.orElse(null);
        novaViagem.setMotorista(motorista);

        Optional<Carro> c = carroRepository.findById(viagem.getCarroId());
        Carro carro = c.orElse(null);
        novaViagem.setCarro(carro);

        novaViagem.setHoraChegada(viagem.getHoraChegada());
        novaViagem.setHoraSaida(viagem.getHoraSaida());
        novaViagem.setQtdPassageiros(viagem.getQtdPassageiros());

        if(fiscal == null || linha == null || carro == null || motorista == null){
            return null;
        } else {
            return novaViagem;
        }
    }

}
