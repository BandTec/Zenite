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
    Funcionario funcionario1 = new Funcionario("123456", "raissa@bandtec.com", false, enderecoF1, Cargo.GERENTE);
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

        String resultado = "Autenticação incorreta";
        for (Conta usuario : usuarios) {
            if (email.equals(usuario.getEmail()) && senha.equals(usuario.getSenha())) {
                usuario.setLogged(true);
                resultado = "Usuário Logado!";
            }
        }
        return resultado;
    }

    @GetMapping("/linhas")
    public Linha getUser() {

        return linhaOeste;
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


