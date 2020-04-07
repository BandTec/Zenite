package orion.zenite.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import orion.zenite.dao.LinhaDao;
import orion.zenite.models.Linha;
import orion.zenite.models.PontoFinal;

import java.util.List;

@Api(description = "Operações relacionadas ao linha", tags = "linha")
@RestController
@RequestMapping("/api/linha")
public class LinhaController {

    @Autowired
    private LinhaDao linhaBD;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Linha> consulta() {

        List<Linha> lista = linhaBD.findAll();
        if(!lista.isEmpty()){
            return lista;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum dado na base de dados.");
    }


    @GetMapping("/pontoIda/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Linha> consultaPonto(@PathVariable int id) {
        PontoFinal p = new PontoFinal();
        p.setId(id);
        List<Linha> lista = linhaBD.findAllByPontoIda(p);
        if(!lista.isEmpty()){
            return lista;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrada nenhuma linha com este ponto inicial.");
    }

}
