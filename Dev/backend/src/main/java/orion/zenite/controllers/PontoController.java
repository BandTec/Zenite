package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import orion.zenite.modelos.ConsultaPaginada;
import orion.zenite.entidades.PontoFinal;
import orion.zenite.repositorios.PontoFinalRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Api(description = "Operações relacionadas ao ponto final", tags = "Ponto Final")
@RestController
@RequestMapping("/api/pontofinal")
public class PontoController {

    @Autowired
    private PontoFinalRepository repository;

    @ApiOperation("Lista todos os pontos de ônibus")
    @GetMapping
    public ResponseEntity consultarPonto( @RequestParam(required = false) Integer pagina,
                                          @RequestParam(required = false) String q
    )  {
        if (this.repository.count() > 0) {
            if(pagina != null) {
                Pageable pageable = PageRequest.of(pagina, 10);
                Page<PontoFinal> page = repository.findAll(pageable);
                ConsultaPaginada consulta = new ConsultaPaginada(page);
                return ok(consulta);
            }
            else if(q != null){
                List<PontoFinal> consulta = repository.findAllByNomeContaining(q);
                return ok(consulta);
            }
            else {
                List<PontoFinal> consulta = repository.findAll();
                return ok(consulta);
            }
        } else {
            return noContent().build();
        }
    }

    @ApiOperation("Exibe ponto por id")
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

    @ApiOperation("Atualiza ponto")
    @PutMapping("{id}")
    public ResponseEntity atualizarPonto( @RequestBody  PontoFinal ponto,
                                          @PathVariable Integer id){
        if (this.repository.existsById(id)) {
            ponto.setId(id);
            this.repository.save(ponto);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @ApiOperation("Exclui um ponto")
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
