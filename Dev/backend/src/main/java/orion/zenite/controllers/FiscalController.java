package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import orion.zenite.dao.ContaDao;
import orion.zenite.dao.DispositivoDao;
import orion.zenite.dao.EnderecoDao;
import orion.zenite.dao.FiscalDao;
import orion.zenite.models.*;

import java.util.List;


@Api(description = "Operações relacionadas ao fiscal", tags = "fiscal")
@RestController
@RequestMapping("/api/fiscal")
public class FiscalController {

    // Classes que realiza consulta no banco de dados
    @Autowired
    private EnderecoDao enderecoBD;

    @Autowired
    private ContaDao contaBD;

    @Autowired
    private FiscalDao fiscalBD;

    @Autowired
    private DispositivoDao dispositivoBD;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("Lista todos os fiscais")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping
    public List<Fiscal> consulta() {

        List<Fiscal> lista = fiscalBD.findAll();
        if(!lista.isEmpty()){
            return lista;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sua requisição não retornou dados");
    }

    @ApiOperation("Buscar um fiscal por seu id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Fiscal não encontrado.")
    })
    @GetMapping("{id}")
    public Fiscal consulta(@PathVariable("id") int id) {
        return fiscalBD
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Fiscal não encontrado"));

    }

    @ApiOperation("Altera um fiscal")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Fiscal não encontrado.")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@RequestBody Fiscal novoFiscal){
        Conta conta = novoFiscal.getConta();
        String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
        conta.setSenha(senhaCriptografada);
        novoFiscal.setConta(conta);
        fiscalBD.save(novoFiscal);
    }

    @ApiOperation("Deleta um fiscal por seu id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Fiscal não encontrado.")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") int id){
        fiscalBD.findById(id)
                .map( fiscal -> {
                    fiscalBD.delete(fiscal );
                    return fiscal;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Fiscal não encontrado") );
    }

    @ApiOperation("Cadastra um fiscal")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Fiscal cadastrado."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 400, message = "Necessário ajustes no corpo da requisição.")
    })
    @PostMapping()
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public Fiscal cadastro(@RequestBody Fiscal novoFiscal) {

        // Encriptar senha
        Conta conta = novoFiscal.getConta();
        String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
        conta.setSenha(senhaCriptografada);
        novoFiscal.setConta(conta);

        // Fiscal
        fiscalBD.save(novoFiscal);
        novoFiscal.setId(fiscalBD.lastId());

        return novoFiscal;
    }
}
