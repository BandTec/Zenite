import React, { useState } from 'react';

import { Container, CaixaHorizontal, CorpoPagina, FormContainer, Titulo, Subtitulo, Caixa } from './styles';
import BotaoForm from '../../components/BotaoForm';
import StatusPage from '../../components/StatusPage';
import MenuLateral from '../../components/MenuLateral';
import InputComRotulo from '../../components/InputComRotulo';
import ComboBoxComRotulo from '../../components/ComboBoxComRotulo';

export default function CadastroLinha() {

  const teste = [
    { value: "01", texto: "917H" },
    { value: "02", texto: "8194" },
    { value: "03", texto: "8002" },
    { value: "04", texto: "8004" }
  ];

  return (
    <Container>
      <MenuLateral />

      <CorpoPagina>

        <FormContainer>

          <Caixa>

            <Subtitulo>CADASTRO DA LINHA</Subtitulo>
            <Titulo>Dados de Acesso</Titulo>

            <CaixaHorizontal>
              <InputComRotulo
                texto="Número da Linha"
                maxLength='7'
                name="numeroLinha"
                type="text"
                required
                pequeno={true}
              />

              <ComboBoxComRotulo 
                texto="Linhas" 
                conteudoCombo={teste} 
                pequeno={true}
              />
            </CaixaHorizontal>

            <CaixaHorizontal>
              <InputComRotulo
                texto="Nome Parada Ida"
                maxLength='80'
                name="paradaIda"
                type="text"
                required
                pequeno={true}
              />

              <ComboBoxComRotulo
                texto="Parada Ida"
                conteudoCombo={teste}
                pequeno={true}
              />
            </CaixaHorizontal>

            <CaixaHorizontal>
              <InputComRotulo
                texto="Nome Parada Volta"
                maxLength='7'
                name="numeroLinha"
                type="text"
                required
                pequeno={true}
              />

              <ComboBoxComRotulo
                texto="Linhas"
                conteudoCombo={teste}
                pequeno={true}
              />
            </CaixaHorizontal>

          </Caixa>

          <BotaoForm texto="Finalizar" concluir={true}  url="/cadastroParadaLinha" />
        </FormContainer>
      </CorpoPagina>

    </Container>
  );
}
