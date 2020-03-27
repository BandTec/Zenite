package orion.zenite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import orion.zenite.models.*;
import orion.zenite.dao.AdministradorDao;
import orion.zenite.dao.ContaDao;

import java.util.List;

/*
 * Todas as rotas que começam com /api/alguma-coisa
 * estão protegidas pelo JWToken.
 * Todas as URI então recebem o token decodificado
 * como um atributo email da requisição
 *
 * a decodificação ocorre na classe /security/JwtFilter

 *
 * *  ALTERAÇÃO
 */
@RestController
@RequestMapping("/api/administrador")
public class AdministradorController {

    @Autowired
    private ContaDao contaBD;

    @Autowired
    private AdministradorDao administradorBD;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Administrador> consultar(){
        List<Administrador> lista = administradorBD.findAll();
        if(!lista.isEmpty()){
            return lista;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista vazia");
    }

    @GetMapping("{id}")
    public Administrador consultar(@PathVariable("id") int id){
        return administradorBD
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Administrador não encontrado"));
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") int id){
        administradorBD.findById(id)
                .map( cliente -> {
                    administradorBD.delete(cliente );
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Administrador não encontrado") );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public Administrador cadastrar(@RequestBody Administrador administrador) {

        // PEGANDO INFORMAÇÕES DO USUÁRIO LOGADO
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(user.getAuthorities().toArray()[0]); // pegando nivel do usuário
        System.out.println(user.getName()); // pegando email do usuário

        // inserir conta
        Conta novaConta = administrador.getConta();
        Nivel nivelAdm = new Nivel();
        nivelAdm.setId(1);
        novaConta.setNivel(nivelAdm);

        // Encriptar senha
        String senhaCriptografada = passwordEncoder.encode(novaConta.getSenha());
        novaConta.setSenha(senhaCriptografada);

        contaBD.save(novaConta);
        administrador.getConta().setIdConta(contaBD.lastId());

        // Fiscal
        administradorBD.save(administrador);
        administrador.setId(administradorBD.lastId());

        return administrador;
    }
}
