import React, { useState } from 'react';

import { Container, CaixaHorizontal, CorpoPagina, FormContainer, Titulo, Subtitulo, Caixa } from './styles';
import BotaoForm from './../../../components/BotaoForm';
import StatusPage from './../../../components/StatusPage';

import InputComRotulo from './../../../components/InputComRotulo';

import { cepMask } from "./../../../functions/Mascaras/mask";

export default function DadosEndereco({ mudarPagina, tipoPagina }) {

  const [valorCep, setValorCep] = useState("");

  const mascararCep = (e) => {
    setValorCep(e.target.value);
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
            ativo={true}
            texto="Endereço"
            temProximoPasso={true}
          />

          <StatusPage
            ativo={false}
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
            <Subtitulo>{tipoPagina} DO GERENTE</Subtitulo>
            <Titulo>Endereço</Titulo>

            <InputComRotulo
              texto="CEP"
              maxLength="8"
              name="cep"
              value={valorCep}
              onChange={mascararCep}
              required
            />

            <InputComRotulo
              texto="Logradouro"
              maxLength="120"
              name="logradouro"
              required
            />

            <CaixaHorizontal>
              <InputComRotulo
                pequeno={true}
                texto="Número"
                maxLength="16"
                name="numero"
                required
              />

              <InputComRotulo
                texto="Complemento"
                pequeno={true}
                maxLength="60"
                name="complemento"
              />
            </CaixaHorizontal>

            <CaixaHorizontal>
              <InputComRotulo
                pequeno={true}
                texto="Cidade"
                maxLength="40"
                name="cidade"
              />

              <InputComRotulo
                texto="Estado"
                pequeno={true}
                maxLength="2"
                name="estado"
              />
            </CaixaHorizontal>
          </Caixa>

          <BotaoForm texto="Próximo" mudarPagina={mudarPagina} />
        </FormContainer>
      </CorpoPagina>
    </Container>
  );
}
