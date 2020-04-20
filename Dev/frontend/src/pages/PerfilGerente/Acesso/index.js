import React, { useState, useEffect } from "react";
import InputComRotulo from "../../../components/InputComRotulo";

import { Titulo } from "./../styles";

export default function Acesso({adicionarDados, dados, validarSenha}) {
  const [valorSenha, setValorSenha] = useState("");
  const [valorConfirmarSenha, setValorConfirmarSenha] = useState("");
  const [email, setEmail] = useState("");

  const verificarSenha = () => {
    if (valorSenha.length >= 8) {
      validarSenha(valorSenha === valorConfirmarSenha);
    }
  };  

  useEffect(() => {
    if (Object.keys(dados).length !== 0) {
      setEmail(dados.conta.email);
    }
  }, []);

  useEffect(()=> {
      adicionarDados({
        conta: {
          idConta: dados.conta.idConta,
          senha: valorSenha,
          email,
        }
      });
     verificarSenha();
  }, [valorSenha, setValorConfirmarSenha,email]);

  return (
    <>
      <Titulo>Dados de Acesso</Titulo>
      <InputComRotulo
        texto="Email"
        maxLength="60"
        name="email"
        type="email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        required
      />

      <InputComRotulo
        texto="Senha"
        maxLength="255"
        name="senha"
        type="password"
        value={valorSenha}
        onChange={(e) => setValorSenha(e.target.value)}
        required
        textoAlerta="Sua senha deve conter no mínimo 8 letras."
      />

      <InputComRotulo
        texto="Confirmar Senha"
        maxLength="255"
        name="confirmarSenha"
        type="password"
        value={valorConfirmarSenha}
        onChange={(e) => setValorConfirmarSenha(e.target.value)}
        required
      />
    </>
  );
}
