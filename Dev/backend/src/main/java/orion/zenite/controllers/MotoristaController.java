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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orion.zenite.modelos.ConsultaPaginada;
import orion.zenite.entidades.*;
import orion.zenite.repositorios.CarroRepository;
import orion.zenite.repositorios.MotoristaCarroRepository;
import orion.zenite.repositorios.MotoristaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;


@Api(description = "Operações relacionadas ao motorista", tags = "motorista")
@RestController
@RequestMapping("/api/motorista")
public class MotoristaController {

    @Autowired
    private MotoristaRepository motoristaBD;

    @Autowired
    private MotoristaCarroRepository repository;

    @Autowired
    private CarroRepository repositoryCarro;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("Lista todos os motoristas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping
    public ResponseEntity consultarTodos(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) String q
    )  {
        if (this.motoristaBD.count() > 0) {
            if(pagina != null) {
                Pageable pageable = PageRequest.of(pagina, 10);
                Page<Motorista> page = motoristaBD.findAll(pageable);
                ConsultaPaginada consulta = new ConsultaPaginada(page);
                return ok(consulta);
            }
            else if(q != null){
                List<Motorista> consulta = motoristaBD.findAllByNomeContaining(q);
                return ok(consulta);
            }
            else {
                List<Motorista> consulta = motoristaBD.findAll();
                return ok(consulta);
            }
        } else {
            return noContent().build();
        }
 }

    @ApiOperation("Busca motorista pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("{id}")
    public ResponseEntity consulta(@PathVariable("id") Integer id){
        Optional<Motorista> motorista = this.motoristaBD.findById(id);

        if(motorista.isPresent()){
            return ok(motorista);
        }else{
            return notFound().build();
        }
    }

    @ApiOperation("Altera um motorista")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Motorista não encontrado.")
    })
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity alterar(@RequestBody Motorista novoMotorista,
                                          @PathVariable Integer id){
        if(this.motoristaBD.existsById(id)) {
            novoMotorista.setId(id);
            Conta conta = novoMotorista.getConta();
            String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
            conta.setSenha(senhaCriptografada);
            novoMotorista.setConta(conta);
            motoristaBD.save(novoMotorista);
            return ok().build();
        }else{
            return notFound().build();
        }
    }

    @ApiOperation("Deleta um motorista por seu ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Motorista não encontrado.")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deletar(@PathVariable("id") Integer id){
        if (this.motoristaBD.existsById(id)) {
            this.motoristaBD.deleteById(id);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @ApiOperation("Cadastra um Motorista")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Necessário ajustes no corpo da requisição.")
    })
    @PostMapping()
    @Transactional // se acontece algum error desfaz os outros dados salvos, faz um rollback
    public ResponseEntity cadastro(@RequestBody Motorista novoMotorista){
        Conta conta = novoMotorista.getConta();
        String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
        conta.setSenha(senhaCriptografada);
        novoMotorista.setConta(conta);
        motoristaBD.save(novoMotorista);

        return created(null).build();
    }

    @ApiOperation("Cadastrar ônibus do motorista")
    @PostMapping("/onibus")
    @Transactional
    public ResponseEntity relacionar(@RequestBody MotoristaCarro novoRelacionamento) {

        MotoristaCarro pesquisa = repository.findByIdMotoristaAndIdCarro(novoRelacionamento.getIdMotorista(), novoRelacionamento.getIdCarro());
        if(pesquisa == null) {
            List<MotoristaCarro> jaTemCarro = repository.findByIdMotorista(novoRelacionamento.getIdMotorista());
            if (!jaTemCarro.isEmpty()){
                for(MotoristaCarro relacionamento : jaTemCarro){
                    repository.delete(relacionamento);
                }
            }
            repository.save(novoRelacionamento);
            return created(null).build();
        }

        return noContent().build();
    }

    @ApiOperation("Listar quais ônibus estão com quais motoristas")
    @GetMapping("/{id}/onibus")
    public ResponseEntity consultarRelacionamento(@PathVariable("id") Integer id) {
        if(motoristaBD.existsById(id)) {
            if (this.repository.count() > 0) {
                List<MotoristaCarro> consulta = this.repository.findByIdMotorista(id);
                ArrayList<Carro> carros = new ArrayList<>();
                for (MotoristaCarro mc : consulta){
                    carros.add(mc.getCarro());
                }
                return ok(carros);
            } else {
                return noContent().build();
            }
        }
        return noContent().build();
    }

}



