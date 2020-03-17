package com.orion.autenticacao.controllers;

import com.orion.autenticacao.models.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/autenticacao")
public class AuthController {

    Endereco enderecoF1 = new Endereco("0129-098", "Rua Assis de Machado",
            "987D", "Casa da Esquisa", "São Paulo", "São Paulo");
    Funcionario funcionario1 = new Motorista("123456", "raissa@bandtec.com",false, 1, "Raissa",
            "098.098.098-12", "12/09/1998", "11", "0987-2345", enderecoF1, "0981238972934");
    Administrador adm = new Administrador("adm123", "adm@zenite.com.br", false, 1, "Administrador");

    List<Conta> usuarios = new ArrayList<>();

    Ponto pontoIda = new Ponto("Vila Mariana");
    Ponto pontoVolta = new Ponto("Terminal Pirituba");
    Linha linhaOeste = new Linha("917H-10", pontoIda, pontoVolta);
    Onibus onibus = new Onibus("112343", linhaOeste);



    @PostMapping("/login/{email}/{senha}")
    public String Login(
            @PathVariable("email") String email,
            @PathVariable("senha") String senha
    ) {

        usuarios.add(funcionario1);
        usuarios.add(adm);

        for (Conta usuario : usuarios) {

            if (usuario.logar(email, senha)) {
                return "Usuário Logado!";
            }
        }
        return "Autenticação incorreta";
    }

    @GetMapping("/usuario")
    public Conta getUser() {
        for (Conta usuario : usuarios) {
            if (usuario.isLogged()) {
                return usuario;
            }
        }
        return null;
    }

    @GetMapping("/logout")
    public String Logout() {
        String resultado = " ";

        for (Conta usuario : usuarios) {
            if (usuario.isLogged()) {
                usuario.setLogged(false);
                resultado = "Você será deslogado...";

            } else {
                resultado = "Usuário não estava Logado!";
            }
        }
        return resultado;
    }

}


