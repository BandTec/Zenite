package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import orion.zenite.entidades.PontoFinal;
import orion.zenite.repositorios.PontoFinalRepository;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Api(description = "Operações relacionadas ao ponto final", tags = "ponto final")
@RestController
@RequestMapping("/api/pontofinal")
public class PontoController {

    @Autowired
    private PontoFinalRepository repository;

    @ApiOperation("Lista todos os pontos de ônibus")
    @GetMapping
    public ResponseEntity consultarPonto() {

        if (this.repository.count() > 0) {
            return ok(this.repository.findAll());
        } else {
            return noContent().build();
        }

    }

    @ApiOperation("Exibe pontos por id")
    @GetMapping("{id}")
    public ResponseEntity getPontoFinal (@PathVariable ("id")Integer id) {
        Optional<PontoFinal> consultaPonto = this.repository.findById(id);

        if (consultaPonto.isPresent()) {
             return ok(consultaPonto.get());
        } else {
            return notFound().build();
        }
    }


    @ApiOperation("Inserir pontos de ônibus")
    @PostMapping
    @Transactional
    public ResponseEntity criarPonto(@RequestBody PontoFinal novoPonto) {
        this.repository.save(novoPonto);

        return created(null).build();

    }

    @ApiOperation("Atualizar Pontos")
    @PutMapping("{id}")
    public ResponseEntity atualizarPonto( @RequestBody  PontoFinal ponto,
                                          @PathVariable int id){
        if (this.repository.existsById(id)) {
            ponto.setId(id);
            this.repository.save(ponto);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirPonto(@PathVariable ("id") Integer id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ok().build();
        } else {
            return notFound().build();
        }

    }
}
