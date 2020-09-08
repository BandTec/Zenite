package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import orion.zenite.modelos.ConsultaPaginada;
import orion.zenite.repositorios.*;
import orion.zenite.entidades.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;


@Api(description = "Operações relacionadas ao fiscal", tags = "Fiscal")
@RestController
@RequestMapping("/api/fiscal")
public class FiscalController {

    // Classes que realiza consulta no banco de dados
    @Autowired
    private FiscalRepository repository;

    @Autowired
    private ViagemRepository viagemRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LinhaRepository linhaRepository;

    @Autowired
    private FiscalLinhaRepository fiscalLinhaRepository;

    @ApiOperation("Lista todos os fiscais")
    @GetMapping
    public ResponseEntity  consultarTodos(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) String q
    )  {
        if (this.repository.count() > 0) {
            if(pagina != null) {
                Pageable pageable = PageRequest.of(pagina, 10);
                Page<Fiscal> page = repository.findAll(pageable);
                ConsultaPaginada consulta = new ConsultaPaginada(page);
                return ok(consulta);
            }
            else if(q != null){
                List<Fiscal> consulta = repository.findAllByNomeIgnoreCaseContaining(q);
                return ok(consulta);
            }
            else {
                List<Fiscal> consulta = repository.findAll();
                return ok(consulta);
            }
        } else {
            return noContent().build();
        }
    }

    @ApiOperation("Buscar um fiscal por seu id")
    @GetMapping("{id}")
    public ResponseEntity consulta(@PathVariable("id") Integer id) {
        Optional<Fiscal> fiscal = this.repository.findById(id);
        if (fiscal.isPresent()) {
            return ok(fiscal);
        } else {
            return notFound().build();
        }
    }

    @ApiOperation("Altera um fiscal")
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity alterar(@RequestBody Fiscal novoFiscal,
                                  @PathVariable Integer id) {
        if (this.repository.existsById(id)) {
            novoFiscal.setId(id);
            // Encriptar senha
            Conta conta = novoFiscal.getConta();
            String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
            conta.setSenha(senhaCriptografada);
            novoFiscal.setConta(conta);

            // alterar fiscal
            this.repository.save(novoFiscal);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @ApiOperation("Deleta um fiscal por seu id")
    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") Integer id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @ApiOperation("Cadastra um fiscal")
    @PostMapping()
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity cadastro(@RequestBody Fiscal novoFiscal) {

        // Encriptar senha
        Conta conta = novoFiscal.getConta();
        String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
        conta.setSenha(senhaCriptografada);
        novoFiscal.setConta(conta);

        // Salvar Fiscal
        this.repository.save(novoFiscal);
        novoFiscal.setId(repository.lastId());

        return created(null).build();
    }

    @ApiOperation("Exibe as linhas que o fiscal trabalha")
    @GetMapping("{id}/linhas")
    public ResponseEntity consultarLinhasPorFiscal(@PathVariable Integer id){
        List<FiscalLinha> fiscalLinha = fiscalLinhaRepository.findByIdFiscal(id);
        List<Linha> linhas = new ArrayList<>();

        for(FiscalLinha f : fiscalLinha){
            Optional<Linha> linha = linhaRepository.findById(f.getIdLinha());
            if(linha.isPresent()){
                linhas.add(linha.get());
            }
        }

        return linhas.isEmpty() ? notFound().build() : ok(linhas);
    }

    @ApiOperation("Remove fiscal de uma linha")
    @DeleteMapping("{idFiscal}/linhas/{idLinha}")
    public ResponseEntity deletarRelacionamentoFiscalLinha(@PathVariable Integer idFiscal, @PathVariable Integer idLinha){
        FiscalLinha fiscalLinha = fiscalLinhaRepository.findByIdFiscalAndIdLinha(idFiscal, idLinha);

        if(fiscalLinha != null){
            fiscalLinhaRepository.delete(fiscalLinha);
            return ok().build();
        }

        return notFound().build();
    }

    @ApiOperation("Cadastra o fiscal em uma linha")
    @PostMapping("/linhas")
    @Transactional
    public ResponseEntity consultarLinhasPorFiscal(@RequestBody FiscalLinha fl){

        FiscalLinha pesquisa = fiscalLinhaRepository.findByIdFiscalAndIdLinha(fl.getIdFiscal(), fl.getIdLinha());
        if(pesquisa == null) {
            List<FiscalLinha> jaTemFiscal = fiscalLinhaRepository.findByIdLinha(fl.getIdLinha());
            if (!jaTemFiscal.isEmpty()){
                for(FiscalLinha relacionamento : jaTemFiscal){
                    fiscalLinhaRepository.delete(relacionamento);
                }
            }
            fiscalLinhaRepository.save(fl);
            return created(null).build();
        }

        return noContent().build();
    }
}
