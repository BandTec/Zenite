package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orion.zenite.dto.ConsultaPaginada;
import orion.zenite.entidades.Carro;
import orion.zenite.entidades.CarroLinha;
import orion.zenite.entidades.Linha;
import orion.zenite.entidades.PontoFinal;
import orion.zenite.repositorios.CarroLinhaRepository;
import orion.zenite.repositorios.CarroRepository;
import orion.zenite.repositorios.LinhaRepository;
import orion.zenite.repositorios.PontoFinalRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Api(description = "Operações relacionadas ao linha", tags = "linha")
@RestController
@RequestMapping("/api/linha")
public class LinhaController {

    @Autowired
    private LinhaRepository linhaBD;

    @Autowired
    private CarroLinhaRepository repository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private PontoFinalRepository pontoFinalRepository;

    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @ApiOperation("Lista todas as linhas de ônibus")
    @GetMapping
    public ResponseEntity consulta(
            @RequestParam(required = false) Integer pagina
    ) {
        if (this.linhaBD.count() > 0) {
            pagina = pagina == null ? 0 : pagina;
            Pageable pageable = PageRequest.of(pagina, 10);
            Page<Linha> page= linhaBD.findAll(pageable);
            ConsultaPaginada consulta = new ConsultaPaginada(page);
            return ResponseEntity.ok(consulta);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    @ApiOperation("Busca linha pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("{id}")
    public Linha consulta(@PathVariable("id") int id){
        return linhaBD
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Linha não encontrado"));
    }

    @ApiOperation("Busca ponto ida pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/pontoIda/{id}")
    public List<Linha> consultaPorPontoIda(@PathVariable("id") int id){
        PontoFinal p = new PontoFinal();
        p.setId(id);
        List<Linha> lista = linhaBD.findAllByPontoIda(p);
        if(!lista.isEmpty()){
            return lista;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrada nenhuma linha com este ponto inicial.");

    }

    @ApiOperation("Busca ponto volta pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/pontoVolta/{id}")
    public List<Linha> consultaPorPontoVolta(@PathVariable("id") int id){
        PontoFinal p = new PontoFinal();
        p.setId(id);
        List<Linha> lista = linhaBD.findAllByPontoVolta(p);
        if(!lista.isEmpty()){
            return lista;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrada nenhuma linha com este ponto inicial.");

    }

    @ApiOperation("Altera uma linha")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Linha não encontrada.")
    })
    @PutMapping
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity alterar(@RequestBody Linha novaLinha){
        PontoFinal ida = novaLinha.getPontoIda();
        PontoFinal volta = novaLinha.getPontoVolta();
        if (ida.getId() == 0){
            pontoFinalRepository.save(ida);
            ida.setId(pontoFinalRepository.lastId());
            novaLinha.setPontoIda(ida);
        }
        if(volta.getId() == 0){
            pontoFinalRepository.save(volta);
            volta.setId(pontoFinalRepository.lastId());
            novaLinha.setPontoVolta(volta);
        }

        linhaBD.save(novaLinha);

        return ResponseEntity.ok().build();
    }

    @ApiOperation("Deleta uma linha por seu ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Linha não encontrado.")
    })
    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") int id){
        linhaBD.findById(id)
                .map( carro -> {
                    linhaBD.delete(carro);
                    return carro;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Linha não encontrada"));
    }

    @ApiOperation("Cadastra uma linha")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Necessário ajustes no corpo da requisição.")
    })
    @PostMapping()
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity cadastro(@RequestBody Linha novaLinha){
        PontoFinal ida = novaLinha.getPontoIda();
        PontoFinal volta = novaLinha.getPontoVolta();
        if (ida.getId() == 0){
            pontoFinalRepository.save(ida);
            ida.setId(pontoFinalRepository.lastId());
            novaLinha.setPontoIda(ida);
        }
        if(volta.getId() == 0){
            pontoFinalRepository.save(volta);
            volta.setId(pontoFinalRepository.lastId());
            novaLinha.setPontoVolta(volta);
        }

        linhaBD.save(novaLinha);

        return ResponseEntity.created(null).build();
    }

    @ApiOperation("Consultar todos os ônibus que atendem linha")
    @GetMapping("/{id}/onibus")
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity consultaCarros(@PathVariable("id") int id){
        List<CarroLinha> onibusLinhas = this.repository.findByIdLinha(id);
        List<Carro> onibus = new ArrayList<>();

        for (CarroLinha c : onibusLinhas) {
            Optional<Carro> carro = carroRepository.findById(c.getIdCarro());
            if(carro.isPresent()){
                onibus.add(carro.get());
            }
        }

        return onibus.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(onibus);
    }

}
