package orion.zenite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orion.zenite.entidades.Dashboard.*;
import orion.zenite.repositorios.dashboard.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    OperandoParadoRepository operandoParadoRepository;

    @Autowired
    ViagemMotoristaRepository viagemMotoristaRepository;

    @Autowired
    DadosLinhaRepository dadosLinhaRepository;

    @Autowired
    DashboardRepository dashboardRepository;

    @Autowired
    ViagemPeriodoRepository viagemPeriodoRepository;

    @Autowired
    OnibusCirculandoRepository onibusCirculandoRepository;

    @GetMapping
    public ResponseEntity getDadosGeral(){
        Optional<OperandoParado> operandoParado = operandoParadoRepository.findOnibusCirculando();
        List<DadosLinha> dadosLinhas = dadosLinhaRepository.findDadosLinha();
        Optional<Integer> carrosNaoAlocados = dashboardRepository.findCarrosNaoAlocados();
        Optional<Integer> tempoMedioViagemHora = dashboardRepository.findTempoMedioViagemHora();
        List<ViagemPeriodo> tempoMedioViagemPeriodo = viagemPeriodoRepository.findTempoMedioViagemHora();

        Dashboard dashboard = new Dashboard();

        dashboard.setOperandoParado(operandoParado);
        dashboard.setDadosLinha(dadosLinhas);
        dashboard.setCarrosNaoAlocados(carrosNaoAlocados);
        dashboard.setTempoMedioViagemHora(tempoMedioViagemHora);
        dashboard.setTempoMedioViagemPeriodo(tempoMedioViagemPeriodo);

        return ok(dashboard);
    }

    @GetMapping("{idLinha}")
    public ResponseEntity getDadosLinha(@PathVariable("idLinha") Integer idLinha){
        List<ViagemMotorista> viagemMotorista = viagemMotoristaRepository.findViagemMotorista(idLinha);
        List<OnibusCirculando> onibusCirculando = onibusCirculandoRepository.findOnibusCirculando(idLinha);
        Dashboard dashboard = new Dashboard();

        dashboard.setViagemMotorista(viagemMotorista);
        dashboard.setOnibusCirculando(onibusCirculando);

        return ok(dashboard);

    }
}
