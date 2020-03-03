package com.orion.autenticacao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

        Usuario user = new Usuario("raissa123", "123456", "Raissa", false);

        @GetMapping("/login/{usuario}/{senha}")
        public  String Login(
                @PathVariable("usuario") String usuario,
                @PathVariable("senha") String senhaUser
        ){
            if(usuario.equals(user.getLogin()) && senhaUser.equals(user.getSenha())){
                user.setLogged(true);
                return "Usuário Logado!";

            }else{
                return "Autenticação incorreta!";
            }
        }

        @GetMapping("/logout")
        public String Logout(){
            if(user.isLogged()){
                user.setLogged(false);
                return "Você será deslogado...";

            }else{
                return "Usuário não estava Logado!";
            }
        }

    }


