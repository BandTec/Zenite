package orion.zenite.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orion.zenite.entidades.Viagem;
import orion.zenite.modelos.ViagemDto;
import orion.zenite.repositorios.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest (classes = ViagemController.class)
class ViagemControllerTest {

    @Autowired
    ViagemController controller;

    @MockBean
    ViagemRepository repository;

    @MockBean
    private FiscalRepository fiscalRepository;

    @MockBean
    private MotoristaRepository motoristaRepository;

    @MockBean
    private CarroRepository carroRepository;

    @MockBean
    private LinhaRepository linhaRepository;


    @Test
    void excluirViagem() {
            //Criando um dublê
            Integer id = 10;
            List <Viagem> viagemList = new ArrayList<>(Arrays.asList(new Viagem(),new Viagem()));

            //id Válido
            Mockito.when(repository.findAll()).thenReturn(viagemList);

            Mockito.when(repository.existsById(id)).thenReturn(true);

            Mockito.doAnswer(tente -> viagemList.remove(0)).when(repository).deleteById(id);

            ResponseEntity respostaDelete = controller.excluirViagem(id);

            assertEquals(200,respostaDelete.getStatusCodeValue());

            //id Inválido

            Mockito.when(repository.existsById(id)).thenReturn(false);

            respostaDelete = controller.excluirViagem(id);

            assertEquals(404,respostaDelete.getStatusCodeValue());
    }


    @Test
    @DisplayName("Testando Anotações")
        void testandoAnotacoes() throws NoSuchMethodException {

        Class classe = ViagemController.class;

        //verificando métodos da classe @RequestController e @RequestMapping
        assertTrue(classe.isAnnotationPresent(RestController.class),"A classe deverá  ter a anotação " +
                "  @RestController");

        assertTrue(classe.isAnnotationPresent(RequestMapping.class),"A classe deverá  ter a anotação " +
                "@RequestMapping");

        ////Cenário de verificação das anotações dos métodos
        Method consulta = classe.getDeclaredMethod("consulta", Integer.class, String.class);
        assertTrue(consulta.isAnnotationPresent(GetMapping.class)," O método consulta() deve estar" +
                "anotado com o @GetMapping");

        Method consultar = classe.getDeclaredMethod("consultar", Integer.class);
        assertTrue(consultar.isAnnotationPresent(GetMapping.class),"O método consultar() deve estar " +
                "anotado com o @GetMapping");

        Method excluirViagem = classe.getDeclaredMethod("excluirViagem", Integer.class);
        assertTrue(excluirViagem.isAnnotationPresent(DeleteMapping.class),"O método excluirViagem() deve " +
                " estar anotado com o @DeleteMapping");

        Method consultarPorLinha = classe.getDeclaredMethod("consultarPorLinha", Integer.class);
        assertTrue(consultarPorLinha.isAnnotationPresent(GetMapping.class),"O método consultarPorLinha() deve " +
                " estar anotado com @GetMapping");

        Method consultarPorOnibus = classe.getDeclaredMethod("consultarPorOnibus", Integer.class);
        assertTrue(consultarPorOnibus.isAnnotationPresent(GetMapping.class),"O método consultarPorOnibus() deve " +
                " estar anotado com @GetMapping");

        Method consultarPorMotorista = classe.getDeclaredMethod("consultarPorMotorista", Integer.class);
        assertTrue(consultarPorMotorista.isAnnotationPresent(GetMapping.class),"O método consultarPorMotorista()" +
                " deve estar com @GetMapping");

        Method alterar = classe.getDeclaredMethod("alterar", ViagemDto.class, Integer.class);
        assertTrue(alterar.isAnnotationPresent(PutMapping.class),"O método alterar() deve estar anotado " +
                "com @PutMapping");

        Method cadastrar = classe.getDeclaredMethod("cadastrar", ViagemDto.class);
        assertTrue(cadastrar.isAnnotationPresent(PostMapping.class),"O método cadastrar() deve estar " +
                "anotado com @PostMapping ");

        //verificando caminhos da URI

        //consultar
        String uriEsperada ="{id}";
        assertEquals(uriEsperada,consultar.getDeclaredAnnotation(GetMapping.class).value()[0]," A URI" +
                " de consultar deve ser " +uriEsperada);

      //excluirViagem
        String uriEsperada2 ="/{id}";
        assertEquals(uriEsperada2,excluirViagem.getDeclaredAnnotation(DeleteMapping.class).value()[0],"A URI de " +
                "excluirViagem deve ser "+uriEsperada2);


        //consultarPorLinha
        String uriEsperada3 = "/linha/{id}";
        assertEquals(uriEsperada3,consultarPorLinha.getDeclaredAnnotation(GetMapping.class).value()[0],"A URI " +
                "de consultarPorLinha deve ser "+uriEsperada3);


        //consultarPorOnibus
        String uriEsperada4 = "/onibus/{id}";
        assertEquals(uriEsperada4,consultarPorOnibus.getDeclaredAnnotation(GetMapping.class).value()[0],"A URI " +
                "de consultarPorOnibus deve ser "+uriEsperada4);

        // consultaPorMotorista
        String uriEsperada5 = "/motorista/{id}";
        assertEquals(uriEsperada5,consultarPorMotorista.getDeclaredAnnotation(GetMapping.class).value()[0]," A " +
                "URI de consultaPorMotorista deve ser "+uriEsperada5);

        //alterar
        String uriEsperado6 = "{id}";
        assertEquals(uriEsperado6,alterar.getDeclaredAnnotation(PutMapping.class).value()[0],"A URI de alterar " +
                "deve ser " +uriEsperado6);

    }

    }
