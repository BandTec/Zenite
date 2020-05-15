package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import orion.zenite.entidades.Carro;
import orion.zenite.entidades.CarroLinha;
import orion.zenite.entidades.Dispositivo;
import orion.zenite.entidades.MotoristaCarro;
import orion.zenite.repositorios.CarroLinhaRepository;
import orion.zenite.repositorios.CarroRepository;
import orion.zenite.repositorios.DispositivoRepository;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Api(description = "Operações relacionadas ao ônibus", tags = "ônibus")
@RestController
@RequestMapping("/api/onibus")
public class CarroController {

    @Autowired
    private CarroRepository repository;

    @Autowired
    private CarroLinhaRepository carroLinhaRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @ApiOperation("Listar todos os ônibus")
    @GetMapping
    public ResponseEntity consulta() {

        if (this.repository.count() > 0) {
            return ok(this.repository.findAll());
        } else {
            return noContent().build();
        }

    }

    @ApiOperation("Exibe ônibus por id")
    @GetMapping("{id}")
    public ResponseEntity consultar(@PathVariable Integer id) {
        Optional<Carro> consultaCarro = this.repository.findById(id);

        if (consultaCarro.isPresent()) {
          return   ok(consultaCarro);
        } else {
            return notFound().build();
        }

    }

    @ApiOperation("Exibe ônibus pelo código do dispositivo")
    @GetMapping("/dispositivo/{codigo}")
    public ResponseEntity consultarPorDispositivo(@PathVariable String codigo) {
        Optional<Dispositivo> dispositivo = dispositivoRepository.findByCodigo(codigo);
        if(dispositivo.isPresent()){
            Optional<Carro> consultaCarro = this.repository.findByDispositivo(dispositivo.get());
            if (consultaCarro.isPresent()) {
                return ok(consultaCarro);
            }
        }
        return notFound().build();
    }

    @ApiOperation("Inserir ônibus")
    @PostMapping
    @Transactional
    public ResponseEntity criarCarro(@RequestBody Carro onibus) {
        this.repository.save(onibus);
        return created(null).build();

    }


    @ApiOperation("Atualizar ônibus")
    @PutMapping("{id}")
    public ResponseEntity atualizarCarro(@RequestBody Carro onibus,
                                         @PathVariable Integer id) {
        if (this.repository.existsById(id)) {
            onibus.setId(id);
            this.repository.save(onibus);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @ApiOperation("Excluir ônibus por id")
    @DeleteMapping("/{id}")
    public ResponseEntity excluirCarro(@PathVariable Integer id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ok().build();
        } else {
            return notFound().build();
        }
    }


    @ApiOperation("Cadastrar linha de um ônibus")
    @PostMapping("/linhas")
    @Transactional
    public ResponseEntity relacionar(@RequestBody CarroLinha novoRelacionamento) {
        this.carroLinhaRepository.save(novoRelacionamento);
        return created(null).build();
    }

    @ApiOperation("Listar quais ônibus estão em quais linhas")
    @GetMapping("/linhas")
    public ResponseEntity consultarRelacionamento() {
        if (this.repository.count() > 0) {
            return ok(this.carroLinhaRepository.findAll());
        } else {
            return noContent().build();
        }
    }

}