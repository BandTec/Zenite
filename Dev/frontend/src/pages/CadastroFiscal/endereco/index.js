import React, { useState } from 'react';

import { Container, CaixaHorizontal, CorpoPagina, FormContainer, Titulo, Subtitulo, Caixa } from './styles';
import BotaoForm from './../../../components/BotaoForm';
import StatusPage from './../../../components/StatusPage';
import MenuLateral from './../../../components/MenuLateral';
import InputComRotulo from './../../../components/InputComRotulo';

import { cpfMask, telefoneMask, dataMask } from "./../../../functions/Mascaras/mask";

export default function CadastroEndereco() {

  const [valorCpf, setValorCpf] = useState("");
  const [valorData, setValorData] = useState("");
  const [valorTelefone, setValorTelefone] = useState("");

  const mascararCpf = (e) => {
    setValorCpf(cpfMask(e.target.value));
  }

  const mascararData = (e) => {
    setValorData(dataMask(e.target.value));
  }

  const mascararTelefone = (e) => {
    setValorTelefone(telefoneMask(e.target.value));
  }

  return (
    <Container>
      <MenuLateral />

      <CorpoPagina>
        <CaixaHorizontal center={true}>
          <StatusPage ativo={false} texto="Dados Pessoais" temProximoPasso={true} />

          <StatusPage ativo={true} texto="Endereço" temProximoPasso={true} />

          <StatusPage ativo={false} texto="Dados de Acesso" temProximoPasso={false} />
        </CaixaHorizontal>

        <FormContainer>
          <BotaoForm texto="Voltar" ladoDireito={false} url="/cadastro1" />

          <Caixa>

            <Subtitulo>CADASTRO DO FISCAL</Subtitulo>
            <Titulo>Endereço</Titulo>

            <InputComRotulo
              texto="Nome"
              maxLength='100'
            />

            <InputComRotulo
              texto="CPF"
              maxLength='14'
              name='cpf'
              value={valorCpf}
              onChange={mascararCpf}
            />

            <InputComRotulo
              texto="Registro Fiscal"
              maxLength='20'
            />

            <CaixaHorizontal>
              <InputComRotulo
                pequeno={true}
                texto="Data de Nascimento"
                maxLength='10'
                name='datadenascimento'
                value={valorData}
                onChange={mascararData}
              />

              <InputComRotulo
                texto="Telefone"
                pequeno={true}
                maxLength='10'
                name='telefone'
                value={valorTelefone}
                onChange={mascararTelefone}
              />

            </CaixaHorizontal>
          </Caixa>

          <BotaoForm texto="Próximo" url="/cadastro3" />
        </FormContainer>
      </CorpoPagina>

    </Container>
  );
}
