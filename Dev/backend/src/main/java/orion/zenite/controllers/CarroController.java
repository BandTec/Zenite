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
import orion.zenite.entidades.*;
import orion.zenite.repositorios.CarroLinhaRepository;
import orion.zenite.repositorios.CarroRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Api(description = "Operações relacionadas ao ônibus", tags = "ônibus")
@RestController
@RequestMapping("/api/onibus")
public class CarroController {

    @Autowired
    private CarroRepository repository;

    @Autowired
    private CarroLinhaRepository carroLinhaRepository;

    @ApiOperation("Listar todos os ônibus")
    @GetMapping
    public ResponseEntity consultarTodos(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) String q
    ) {
        if (this.repository.count() > 0) {
            if (pagina != null) {
                Pageable pageable = PageRequest.of(pagina, 10);
                Page<Carro> page = repository.findAll(pageable);
                ConsultaPaginada consulta = new ConsultaPaginada(page);
                return ok(consulta);
            } else if (q != null) {
                List<Carro> consulta = repository.findAllByNumeroContainingIgnoreCase(q);
                return ok(consulta);
            } else {
                List<Carro> consulta = repository.findAll();
                return ok(consulta);
            }
        } else {
            return noContent().build();
        }
    }

    @ApiOperation("Exibe ônibus por id")
    @GetMapping("{id}")
    public ResponseEntity consultar(@PathVariable Integer id) {
        Optional<Carro> consultaCarro = this.repository.findById(id);

        if (consultaCarro.isPresent()) {
          return   ok(consultaCarro);
        } else {
            return notFound().build();
        }

    }

    @ApiOperation("Inserir ônibus")
    @PostMapping
    @Transactional
    public ResponseEntity criarCarro(@RequestBody Carro onibus) {
        this.repository.save(onibus);
        return created(null).build();

    }


    @ApiOperation("Atualizar ônibus")
    @PutMapping("{id}")
    public ResponseEntity atualizarCarro(@RequestBody Carro onibus,
                                         @PathVariable Integer id) {
        if (this.repository.existsById(id)) {
            onibus.setId(id);
            this.repository.save(onibus);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @ApiOperation("Excluir ônibus por id")
    @DeleteMapping("/{id}")
    public ResponseEntity excluirCarro(@PathVariable Integer id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ok().build();
        } else {
            return notFound().build();
        }
    }


    @ApiOperation("Cadastrar linha de um ônibus")
    @PostMapping("/linhas")
    @Transactional
    public ResponseEntity relacionar(@RequestBody CarroLinha novoRelacionamento) {

        CarroLinha pesquisa = carroLinhaRepository.findByIdLinhaAndIdCarro(novoRelacionamento.getIdLinha(), novoRelacionamento.getIdCarro());
        if(pesquisa == null) {
            List<CarroLinha> jaTemLinha = carroLinhaRepository.findByIdCarro(novoRelacionamento.getIdCarro());
            if (!jaTemLinha.isEmpty()){
                for(CarroLinha relacionamento : jaTemLinha){
                    carroLinhaRepository.delete(relacionamento);
                }
            }
            carroLinhaRepository.save(novoRelacionamento);
            return created(null).build();
        }

        return noContent().build();
    }

    @ApiOperation("Listar quais ônibus estão em quais linhas")
    @GetMapping("/linhas")
    public ResponseEntity consultarRelacionamento() {
        if (this.repository.count() > 0) {
            return ok(this.carroLinhaRepository.findAll());
        } else {
            return noContent().build();
        }
    }

}