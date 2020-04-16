import React, { useState } from 'react';
import { Link } from 'react-router-dom';

import { Container, CorpoPagina, FormContainer, Titulo, Subtitulo, Caixa } from './styles';
import BotaoForm from './../../components/BotaoForm';
import InputComRotulo from './../../components/InputComRotulo';

export default function CadastroAdmin() {

  const [valorSenha, setValorSenha] = useState("");
  const [valorConfirmarSenha, setValorConfirmarSenha] = useState("");
  const [validacaoSenha, setValidacaoSenha] = useState("");

  const verificarSenha = () => {
      // verificao
    setValidacaoSenha(valorSenha === valorConfirmarSenha ? true : false);
    console.log(validacaoSenha);
    console.log(valorSenha);
    console.log(valorConfirmarSenha);
  }

  return (
    <Container>
      <CorpoPagina>
        <FormContainer>
          <Link to="/admin">
            <BotaoForm texto="VOLTAR" ladoDireito={false} />
          </Link>

          <Caixa>
            <Subtitulo>CADASTRO DO ADMINISTRADOR</Subtitulo>
            <Titulo>Dados de Acesso</Titulo>

            <InputComRotulo
              texto="Nome"
              maxLength="100"
              name="nome"
              type="text"
              required
            />

            <InputComRotulo
              texto="Email"
              maxLength="60"
              name="email"
              type="email"
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
              onChange={(e) => {
                setValorConfirmarSenha(e.target.value);
                verificarSenha();
              }}
              required
              invalido={validacaoSenha}
            />
          </Caixa>
          <Link to="/admin">
            <BotaoForm texto="Finalizar" concluir={true} />
          </Link>
        </FormContainer>
      </CorpoPagina>
    </Container>
  );
}
