package orion.zenite.controllers;

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
import orion.zenite.dto.FiscalRequest;
import orion.zenite.models.*;

import java.util.List;
import java.util.Optional;

/*
 * Todas as rotas que começam com /api/alguma-coisa
 * estão protegidas pelo JWToken.
 * Todas as URI então recebem o token decodificado
 * como um atributo email da requisição
 *
 * a decodificação ocorre na classe /security/JwtFilter
 */
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Fiscal> consulta() {

        List<Fiscal> lista = fiscalBD.findAll();
        if(!lista.isEmpty()){
            return lista;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista vazia");
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Fiscal consulta(@PathVariable("id") int id) {
        return fiscalBD
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Administrador não encontrado"));

    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@RequestBody Fiscal novoFiscal){
        Conta conta = novoFiscal.getConta();
        String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
        conta.setSenha(senhaCriptografada);

        contaBD.save(conta);
        novoFiscal.setConta(conta);

        enderecoBD.save(novoFiscal.getEndereco());
        dispositivoBD.save(novoFiscal.getDispositivo());
        fiscalBD.save(novoFiscal);
    }

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

    @PostMapping()
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public Fiscal cadastro(@RequestBody Fiscal novoFiscal) {
Conta conta = novoFiscal.getConta();

        // Encriptar senha
        String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
        conta.setSenha(senhaCriptografada);
        contaBD.save(conta);
        conta.setIdConta(contaBD.lastId());
        novoFiscal.setConta(conta);

        // inserir endereco
        Endereco endereco = novoFiscal.getEndereco();
        enderecoBD.save(endereco);
        endereco.setId(enderecoBD.lastId());
        novoFiscal.setEndereco(endereco);

        //Dispositivo
        dispositivoBD.save(novoFiscal.getDispositivo());
        novoFiscal.getDispositivo().setId(dispositivoBD.lastId());

        // Fiscal
        fiscalBD.save(novoFiscal);
        novoFiscal.setId(fiscalBD.lastId());

        return novoFiscal;
    }
}
