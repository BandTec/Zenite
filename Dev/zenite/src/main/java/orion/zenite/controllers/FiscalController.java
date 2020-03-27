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
import orion.zenite.models.Conta;
import orion.zenite.models.Endereco;
import orion.zenite.models.Fiscal;
import orion.zenite.models.Nivel;

import java.util.List;

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

    @PostMapping("cadastro")
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public Fiscal cadastro(@RequestBody Fiscal fiscal) {

        // inserir conta
        Conta novaConta = fiscal.getConta();
        Nivel nivelAdm = new Nivel();
        nivelAdm.setId(3);
        novaConta.setNivel(nivelAdm);

        // Encriptar senha
        String senhaCriptografada = passwordEncoder.encode(novaConta.getSenha());
        novaConta.setSenha(senhaCriptografada);

        contaBD.save(novaConta);
        fiscal.getConta().setIdConta(contaBD.lastId());

        // inserir endereco
        Endereco endereco = fiscal.getEndereco();
        enderecoBD.save(endereco);
        endereco.setId(enderecoBD.lastId());
        fiscal.setEndereco(endereco);

        //Dispositivo
        dispositivoBD.save(fiscal.getDispositivo());
        fiscal.getDispositivo().setId(dispositivoBD.lastId());

        // Fiscal
        fiscalBD.save(fiscal);
        fiscal.setId(fiscalBD.lastId());

        return fiscal;
    }
}
