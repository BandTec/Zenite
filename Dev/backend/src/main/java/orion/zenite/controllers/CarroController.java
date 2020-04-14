package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import orion.zenite.entidades.Carro;
import orion.zenite.repositorios.CarroRepository;

import java.util.Optional;

@Api(description = "Operações relacionadas ao ônibus", tags = "ônibus")
@RestController
@RequestMapping("/api/onibus")
public class CarroController {

    @Autowired
    private CarroRepository repository;

    @ApiOperation("Listar todos os ônibus")
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
    public ResponseEntity consultar(@PathVariable Integer id) {
        Optional<Carro> consultaCarro = this.repository.findById(id);

        if (consultaCarro.isPresent()) {
          return   ResponseEntity.ok(consultaCarro);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @ApiOperation("Inserir pontos de ônibus")
    @PostMapping
    @Transactional
    public ResponseEntity criarCarro(@RequestBody Carro novoCarro) {
        this.repository.save(novoCarro);
        return ResponseEntity.created(null).build();

    }


    @ApiOperation("Atualizar ônibus")
    @PutMapping
    public ResponseEntity atualizarCarro(@RequestBody Carro onibus) {
        if (this.repository.existsById(onibus.getId())) {
            this.repository.save(onibus);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirCarro(@PathVariable Integer id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}