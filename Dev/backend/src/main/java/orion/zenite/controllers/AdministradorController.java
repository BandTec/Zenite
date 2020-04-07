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
import orion.zenite.models.*;
import orion.zenite.dao.AdministradorDao;
import orion.zenite.dao.ContaDao;

import java.util.List;
import java.util.Optional;

/*
 * Todas as rotas que começam com /api/alguma-coisa
 * estão protegidas pelo JWToken.
 * Todas as URI então recebem o token decodificado
 * como um atributo email da requisição
 *
 * a decodificação ocorre na classe config/security/JwtFilter
 */
@Api(description = "Operações relacionados ao administrador", tags = "administrador")
@RestController
@RequestMapping("/api/administrador")
public class AdministradorController {

    @Autowired
    private ContaDao contaBD;

    @Autowired
    private AdministradorDao administradorBD;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
        // PEGANDO INFORMAÇÕES DO USUÁRIO LOGADO
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(user.getAuthorities().toArray()[0]); // pegando nivel do usuário
        System.out.println(user.getName()); // pegando email do usuário

     */

    @ApiOperation("Lista todos os administradores")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping
    public List<Administrador> consultar(){
        List<Administrador> lista = administradorBD.findAll();
        if(!lista.isEmpty()){
            return lista;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista vazia");
    }

    @ApiOperation("Buscar um administrador por seu id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Administrador não encontrado.")
    })
    @GetMapping("{id}")
    public Administrador consultar(@PathVariable("id") int id){
        return administradorBD
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Administrador não encontrado"));
    }

    @ApiOperation("Deleta um administrador por seu id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Administrador não encontrado.")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") int id){
        administradorBD.findById(id)
                .map( adm -> {
                    administradorBD.delete(adm);
                    return adm;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Administrador não encontrado") );
    }

    @ApiOperation("Altera um administrador")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Administrador não encontrado.")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@RequestBody Administrador administrador){
        // Devido a implementação na classe Adminsitrador da propriedade CascadeType.ALL
        // ao usar o método save()
        // para salvar o administrador ele automaticamente
        // salve a conta associada a ele
        Conta conta = administrador.getConta();
        String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
        conta.setSenha(senhaCriptografada);
        administrador.getConta();
        administradorBD.save(administrador);
    }

    @ApiOperation("Cadastra um administrador")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Administrador cadastrado."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 400, message = "Necessário ajustes no corpo da requisição.")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public Administrador cadastrar(@RequestBody Administrador administrador) {
        Conta conta = administrador.getConta();

        // Encriptar senha
        String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
        conta.setSenha(senhaCriptografada);
        administrador.setConta(conta);

        administradorBD.save(administrador);
        administrador.setId(administradorBD.lastId());

        return administrador;
    }
}
