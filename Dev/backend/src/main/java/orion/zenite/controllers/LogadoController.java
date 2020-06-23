package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orion.zenite.entidades.*;
import orion.zenite.repositorios.*;

import javax.management.relation.Role;
import java.util.Optional;

@Api(description = "Retorna informações do usuário logado",  tags = "Dados do usuário logado")
@RestController
@RequestMapping("/logado")
public class LogadoController {

    @Autowired
    private FiscalRepository fiscalRepository;

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private AdministradorRepository admRepository;

    @Autowired
    private ContaRepository contaRepository;

    @ApiOperation("Retorna dados do usuário logado a quem pertence o jwt token")
    @GetMapping
    public ResponseEntity consulta() {
        // PEGANDO INFORMAÇÕES DO USUÁRIO LOGADO
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        GrantedAuthority role = (GrantedAuthority) user.getAuthorities().toArray()[0]; // pegando nivel do usuário
        Optional<Conta> conta = contaRepository.findByEmail(user.getName());
        if (conta.isPresent()) {
            switch (role.getAuthority()) {
                case "ROLE_ADMIN":
                    Optional<Administrador> adm = admRepository.findByConta(conta.get());
                    return adm.isPresent() ? ResponseEntity.ok(adm.get()) : ResponseEntity.notFound().build();
                case "ROLE_FISCAL":
                    Optional<Fiscal> fiscal = fiscalRepository.findByConta(conta.get());
                    return fiscal.isPresent() ? ResponseEntity.ok(fiscal.get()) : ResponseEntity.notFound().build();
                case "ROLE_GERENTE":
                    Optional<Gerente> gerente = gerenteRepository.findByConta(conta.get());
                    return gerente.isPresent() ? ResponseEntity.ok(gerente.get()) : ResponseEntity.notFound().build();
                case "ROLE_MOTORISTA":
                    Optional<Motorista> motorista = motoristaRepository.findByConta(conta.get());
                    return motorista.isPresent() ? ResponseEntity.ok(motorista.get()) : ResponseEntity.notFound().build();
                default:
                    return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
}
