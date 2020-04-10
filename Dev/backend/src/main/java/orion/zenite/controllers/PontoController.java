package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orion.zenite.repositorios.PontoFinalRepository;

@Api(description = "Operações relacionadas ao ponto final", tags = "ponto final")
@RestController
@RequestMapping("/api/pontofinal")
public class PontoController {

    @Autowired
    private PontoFinalRepository repository;

    @ApiOperation("Lista todos os paradas de ônibus")
    @GetMapping
    public ResponseEntity consulta() {

        if (this.repository.count() > 0) {
            return ResponseEntity.ok(this.repository.findAll());
        } else {
            return ResponseEntity.noContent().build();
        }

    }
}
