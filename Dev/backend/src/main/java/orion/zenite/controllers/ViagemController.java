package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import orion.zenite.dto.ViagemDto;
import orion.zenite.entidades.Viagem;
import orion.zenite.repositorios.ViagemRepository;

import java.util.Optional;

@Api(description = "Operações relacionadas a viagem", tags = "viagem")
@RestController
@RequestMapping("/api/viagem")
public class ViagemController {

    @Autowired
    private ViagemRepository repository;

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
    public ResponseEntity consultar(@PathVariable ("id")Integer id) {
        Optional<Viagem> consultaViagem = this.repository.findById(id);

        if (consultaViagem.isPresent()) {
          return  ResponseEntity.ok(consultaViagem);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @ApiOperation("Inserir viagem")
    @PostMapping
    @Transactional
    public ResponseEntity criarViagem(@RequestBody Viagem novaViagem) {
        this.repository.save(novaViagem);
        return ResponseEntity.created(null).build();

    }

    @ApiOperation("Atualizar Viagem")
    @PutMapping
    public ResponseEntity atualizarViagem(@RequestBody ViagemDto outraViagem) {
        if (this.repository.existsById(outraViagem.getId())) {
            this.repository.findById(outraViagem.getId()).get();
            Viagem viagemUpdate = this.repository.findById(outraViagem.getId()).get();
            this.repository.save(viagemUpdate);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirViagem(@PathVariable ("id") Integer id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
