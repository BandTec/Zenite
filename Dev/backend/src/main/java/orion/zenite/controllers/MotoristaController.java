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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orion.zenite.entidades.Carro;
import orion.zenite.entidades.Conta;
import orion.zenite.entidades.Motorista;
import orion.zenite.entidades.MotoristaCarro;
import orion.zenite.repositorios.CarroRepository;
import orion.zenite.repositorios.MotoristaCarroRepository;
import orion.zenite.repositorios.MotoristaRepository;

import java.util.Optional;


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
    public ResponseEntity consulta() {
        if (this.motoristaBD.count() > 0) {
            return ResponseEntity.ok(this.motoristaBD.findAll());
        } else {
            return ResponseEntity.noContent().build();
        }

 }

    @ApiOperation("Busca motorista pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Sua requisição não retornou dados.")
    })
    @GetMapping("{id}")
    public Motorista consulta(@PathVariable("id") int id){
        return motoristaBD
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Motorista não encontrado"));
    }

    @ApiOperation("Altera um motorista")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Motorista não encontrado.")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@RequestBody Motorista novoMotorista){
        Conta conta = novoMotorista.getConta();
        String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
        conta.setSenha(senhaCriptografada);
        novoMotorista.setConta(conta);
        motoristaBD.save(novoMotorista);
    }

    @ApiOperation("Deleta um motorista por seu ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário sem nivel de autorização."),
            @ApiResponse(code = 404, message = "Motorista não encontrado.")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") int id){
        motoristaBD.findById(id)
                .map( carro -> {
                    motoristaBD.delete(carro);
                    return carro;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Motorista não encontrado"));
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

        return ResponseEntity.created(null).build();
    }

    @ApiOperation("Cadastrar ônibus do motorista")
    @PostMapping("/onibus")
    @Transactional
    public ResponseEntity relacionar(@RequestBody MotoristaCarro novoRelacionamento) {
        this.repository.save(novoRelacionamento);
        return ResponseEntity.created(null).build();
    }

    @ApiOperation("Listar quais ônibus estão com quais motoristas")
    @GetMapping("/onibus")
    public ResponseEntity consultarRelacionamento() {
        if (this.repository.count() > 0) {
            return ResponseEntity.ok(this.repository.findAll());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}



