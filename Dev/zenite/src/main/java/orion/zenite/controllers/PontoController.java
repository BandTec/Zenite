package orion.zenite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import orion.zenite.dao.PontoFinalDao;
import orion.zenite.models.PontoFinal;

import java.util.List;

@RestController
@RequestMapping("/api/pontofinal")
public class PontoController {

    @Autowired
    private PontoFinalDao pontoBD;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PontoFinal> consulta() {

        List<PontoFinal> lista = pontoBD.findAll();
        if(!lista.isEmpty()){
            return lista;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista vazia");

    }
}
