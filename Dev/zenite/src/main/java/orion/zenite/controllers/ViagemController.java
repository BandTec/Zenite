package orion.zenite.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import orion.zenite.dao.ViagemDao;
import orion.zenite.models.Viagem;
import java.util.List;

@Api(description = "Operações relacionadas a viagem", tags = "viagem")
@RestController
@RequestMapping("/api/viagem")
public class ViagemController {

    @Autowired
    private ViagemDao viagemBD;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Viagem> consulta() {

        List<Viagem> lista = viagemBD.findAll();
        if(!lista.isEmpty()){
            return lista;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista vazia");
    }


}
