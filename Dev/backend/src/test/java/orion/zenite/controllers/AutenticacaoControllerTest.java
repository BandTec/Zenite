package orion.zenite.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import orion.zenite.config.security.JwtService;
import orion.zenite.config.security.LoginService;
import orion.zenite.exceptions.SenhaInvalidaExcepton;
import orion.zenite.modelos.LoginRequest;
import orion.zenite.modelos.ResponstaApi;
import orion.zenite.repositorios.ContaRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes ={AutenticacaoController.class,JwtService.class})
class AutenticacaoControllerTest {


    @Autowired
    AutenticacaoController controller;

    // Classe que gera JWToken
    @MockBean
    private JwtService jwt;

    // Classe que realiza consulta no banco de dados
    @MockBean
    private ContaRepository contaBD;

    @MockBean
    private LoginService loginService;


    @Test
    void login() {

        String email= "lais.silva@zenite.com.br";
        String senha= "123456789";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setSenha(senha);

        Mockito.when(loginService.autenticar(loginRequest)).thenReturn(User
                .builder()
                .username(email)
                .password(senha)
                .roles("admin")
                .build());

        Mockito.when(jwt.codificarToken(email)).thenReturn("xBgWtZyuA86ukaedm9HyPr/4hmLBRBzmzBfa+KP9wyE=");

        ResponseEntity respostaMetodo = controller.login(loginRequest);

        assertEquals(200,respostaMetodo.getStatusCodeValue(),"Esse método deve retornar 200");

        ResponstaApi resposta= (ResponstaApi) respostaMetodo.getBody();

        assertEquals("Bearer xBgWtZyuA86ukaedm9HyPr/4hmLBRBzmzBfa+KP9wyE=",resposta.getMessage(),"Esse " +
                " método deve retornar a palavra Bearer e o Token");


        email= "lais.silva@zenite.com.br";
        senha= "1234567";

        loginRequest.setEmail(email);
        loginRequest.setSenha(senha);

        Mockito.when(loginService.autenticar(loginRequest)).thenThrow( new SenhaInvalidaExcepton());

        respostaMetodo = controller.login(loginRequest);

        assertEquals(401,respostaMetodo.getStatusCodeValue(),"Esse método deve retornar 401");

        resposta= (ResponstaApi) respostaMetodo.getBody();

        assertEquals("Senha Inválida",resposta.getMessage(),"Esse " +
                " método deve retornar a mensagem de erro : Senha inválida ");

    }
}