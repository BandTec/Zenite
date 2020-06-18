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

    @Autowired
    ViagemPeriodoLinhaRepository viagemPeriodoLinhaRepository;

    @Autowired
    TempoMedioViagemDiaDaSemanaRepository tempoMedioViagemDiaDaSemanaRepository;

    @GetMapping
    public ResponseEntity getDadosGeral(){
        Optional<OperandoParado> operandoParado = operandoParadoRepository.findOnibusCirculando();
        List<DadosLinha> dadosLinhas = dadosLinhaRepository.findDadosLinha();
        Optional<Integer> carrosNaoAlocados = dashboardRepository.findCarrosNaoAlocados();
        Optional<Integer> tempoMedioViagemHora = dashboardRepository.findTempoMedioViagemHora();
        List<ViagemPeriodo> tempoMedioViagemPeriodo = viagemPeriodoRepository.findTempoMedioViagemHora();

        DashboardGeral dashboardGeral = new DashboardGeral();

        dashboardGeral.setOperandoParado(operandoParado);
        dashboardGeral.setDadosLinha(dadosLinhas);
        dashboardGeral.setCarrosNaoAlocados(carrosNaoAlocados);
        dashboardGeral.setTempoMedioViagemHora(tempoMedioViagemHora);
        dashboardGeral.setTempoMedioViagemPeriodo(tempoMedioViagemPeriodo);

        return ok(dashboardGeral);
    }

    @GetMapping("{idLinha}")
    public ResponseEntity getDadosLinha(@PathVariable("idLinha") Integer idLinha){
        List<ViagemMotorista> viagemMotorista = viagemMotoristaRepository.findViagemMotorista(idLinha);
        List<OnibusCirculando> onibusCirculando = onibusCirculandoRepository.findOnibusCirculando(idLinha);
        List<ViagemPeriodoLinha> viagemPeriodoLinha = viagemPeriodoLinhaRepository.findViagemPeriodoLinha(idLinha);
        List<TempoMedioViagemDiaDaSemana> tempoMedioViagemDiaDaSemana = tempoMedioViagemDiaDaSemanaRepository.findTempoMedioViagemDiaDaSemana(idLinha);
        DadosLinha dadosLinha = dadosLinhaRepository.findDadosLinhaLinha(idLinha);
        DashboardLinha dashboardLinha = new DashboardLinha();

        dashboardLinha.setViagemMotorista(viagemMotorista);
        dashboardLinha.setOnibusCirculando(onibusCirculando);
        dashboardLinha.setViagemPeriodoLinha(viagemPeriodoLinha);
        dashboardLinha.setTempoMedioViagemDiaDaSemana(tempoMedioViagemDiaDaSemana);
        dashboardLinha.setOnibusAlocados(dadosLinha.getQtdCarrosCirculando());
        dashboardLinha.setMotoristasAlocados(dadosLinha.getQtdMotorista());
        dashboardLinha.setFiscalResponsavel(dadosLinha.getFiscalIda());
        dashboardLinha.setNumeroLinha(dadosLinha.getNumeroLinha());

        return ok(dashboardLinha);

    }
}
