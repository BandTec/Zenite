package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orion.zenite.entidades.PontoFinal;
import orion.zenite.repositorios.LinhaRepository;

@Api(description = "Operações relacionadas ao linha", tags = "linha")
@RestController
@RequestMapping("/api/linha")
public class LinhaController {

    @Autowired
    private LinhaRepository repository;

    @ApiOperation("Lista todas as linhas de ônibus")
    @GetMapping
    public ResponseEntity consulta() {

        if (this.repository.count() > 0) {
            return ResponseEntity.ok(this.repository.findAll());
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @ApiOperation("Lista todas as linhas de ônibus de um determinada parada inicial")
    @GetMapping("/pontoIda/{id}")
    public ResponseEntity consultaPonto(@PathVariable int id) {
        PontoFinal pontoInicial = new PontoFinal();
        pontoInicial.setId(id);
        if (this.repository.count() > 0) {
            return ResponseEntity.ok(this.repository.findAllByPontoIda(pontoInicial));
        } else {
            return ResponseEntity.noContent().build();
        }
    }


}
