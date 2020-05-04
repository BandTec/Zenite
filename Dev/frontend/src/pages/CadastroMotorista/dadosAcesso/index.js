import React, { useState } from 'react';

import { Container, CaixaHorizontal, CorpoPagina, FormContainer, Titulo, Subtitulo, Caixa } from './styles';
import BotaoForm from './../../../components/BotaoForm';
import StatusPage from './../../../components/StatusPage';

import InputComRotulo from './../../../components/InputComRotulo';

export default function DadosAcesso({ mudarPagina, tipoPagina, adicionarDados }) {

  const [email, setEmail] = useState("");
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

  const criarJson = () => {
    adicionarDados({
      "conta": {
        "senha":valorSenha,
        email,
        "nivel": {
          "id": 4
        }
      }
    })
}

  return (
    <Container>


      <CorpoPagina>
        <CaixaHorizontal center={true}>
          <StatusPage
            ativo={false}
            texto="Dados Pessoais"
            temProximoPasso={true}
          />

          <StatusPage
            ativo={false}
            texto="Endereço"
            temProximoPasso={true}
          />

          <StatusPage
            ativo={true}
            texto="Dados de Acesso"
            temProximoPasso={false}
          />
        </CaixaHorizontal>

        <FormContainer>
          <BotaoForm
            texto="Voltar"
            ladoDireito={false}
            mudarPagina={mudarPagina}
          />

          <Caixa>
            <Subtitulo>{tipoPagina} DO MOTORISTA</Subtitulo>
            <Titulo>Dados de Acesso</Titulo>

            <InputComRotulo
              texto="Email"
              maxLength="60"
              name="email"
              type="email"
              value={email}
              onChange={e => setEmail(e.target.value)}
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

          <BotaoForm
            texto="Finalizar"
            concluir={true}
            mudarPagina={mudarPagina}
            criarJson={criarJson}
          />
        </FormContainer>
      </CorpoPagina>
    </Container>
  );
}
