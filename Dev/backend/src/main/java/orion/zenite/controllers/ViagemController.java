package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orion.zenite.repositorios.ViagemRepository;

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


}
