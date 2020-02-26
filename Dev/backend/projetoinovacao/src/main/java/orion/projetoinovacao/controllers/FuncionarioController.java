package orion.projetoinovacao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orion.projetoinovacao.model.Funcionario;
import orion.projetoinovacao.payload.ApiResponse;
import orion.projetoinovacao.repository.FuncionarioRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @PostMapping("/cadastro")
    public ResponseEntity<?> testeInsert(@Valid @RequestBody Funcionario funcionario) {
        repository.save(funcionario);


        return ResponseEntity.ok(new ApiResponse(true, "ola"));
    }

    @GetMapping("/consulta")
    public Iterable<Funcionario> testeSelect() {
        return repository.findAll();


    }

}
