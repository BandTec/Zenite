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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orion.zenite.dto.ConsultaPaginada;
import orion.zenite.entidades.*;
import orion.zenite.repositorios.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;


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
    private FiscalRepository fiscalRepository;

    @Autowired
    private PontoFinalRepository pontoFinalRepository;

    @Autowired
    private FiscalLinhaRepository fiscalLinhaRepository;

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
            return ok(consulta);
        } else {
            return noContent().build();
        }
    }


    @ApiOperation("Busca linha pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("{id}")
    public ResponseEntity consultaLinha(@PathVariable("id") Integer id){
        Optional<Linha> consultaLinha = this.linhaBD.findById(id);

        if(consultaLinha.isPresent()){
            return ok(consultaLinha);
        }else{
            return notFound().build();
        }
    }

    @ApiOperation("Busca ponto ida pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/pontoIda/{id}")
    public ResponseEntity consultaPorPontoIda(@PathVariable("id") Integer id){
        PontoFinal p = new PontoFinal();
        p.setId(id);
        List<Linha> lista = linhaBD.findAllByPontoIda(p);
        if(!lista.isEmpty()){
            return ok(lista);
        }
        return notFound().build();
    }

    @ApiOperation("Busca ponto volta pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("/pontoVolta/{id}")
    public ResponseEntity consultaPorPontoVolta(@PathVariable("id") Integer id){
        PontoFinal p = new PontoFinal();
        p.setId(id);
        List<Linha> lista = linhaBD.findAllByPontoVolta(p);
        if(!lista.isEmpty()){
            return ok(lista);
        }
        return notFound().build();

    }

    @ApiOperation("Altera uma linha")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Linha não encontrada.")
    })
    @PutMapping("{id}")
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity alterar(@RequestBody Linha novaLinha,
                                  @PathVariable Integer id){
        novaLinha.setId(id);
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

        return ok().build();
    }

    @ApiOperation("Deleta uma linha por seu ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Linha não encontrado.")
    })
    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") Integer id){
        if (this.linhaBD.existsById(id)) {
            this.linhaBD.deleteById(id);
            return ok().build();
        } else {
            return notFound().build();
        }
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

        return created(null).build();
    }

    @ApiOperation("Consultar todos os ônibus que atendem linha")
    @GetMapping("/{id}/onibus")
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity consultaCarros(@PathVariable("id") Integer id){
        List<CarroLinha> onibusLinhas = this.repository.findByIdLinha(id);
        List<Carro> onibus = new ArrayList<>();

        for (CarroLinha c : onibusLinhas) {
            Optional<Carro> carro = carroRepository.findById(c.getIdCarro());
            if(carro.isPresent()){
                onibus.add(carro.get());
            }
        }

        return onibus.isEmpty() ? notFound().build() : ok(onibus);
    }

    @ApiOperation("Consultar todos os fiscais responsaveis por determinada linha")
    @GetMapping("/{id}/fiscal")
    public ResponseEntity consultaFiscais(@PathVariable("id") Integer id){
        List<FiscalLinha> fiscalLinhas = fiscalLinhaRepository.findByIdLinha(id);
        List<Fiscal> fiscal = new ArrayList<>();

        for(FiscalLinha f : fiscalLinhas){
            Optional<Fiscal> fiscal1 =  fiscalRepository.findById(f.getIdFiscal());
            if(fiscal1.isPresent()){
                fiscal.add(fiscal1.get());
            }
        }

        return fiscal.isEmpty() ? notFound().build() : ok(fiscal);
    }

}
