import React, { useState } from 'react';

import { Container, CaixaHorizontal, CorpoPagina, FormContainer, Titulo, Subtitulo, Caixa } from './styles';
import BotaoForm from '../../components/BotaoForm';
import StatusPage from '../../components/StatusPage';
import MenuLateral from '../../components/MenuLateral';
import InputComRotulo from '../../components/InputComRotulo';

export default function CadastroOnibus() {
  return (
    <Container>
      <MenuLateral />

      <CorpoPagina>
        <FormContainer>

          <Caixa>

            <Subtitulo>CADASTRO DO ÔNIBUS</Subtitulo>
            <Titulo>Dados</Titulo>

            <InputComRotulo
              texto="NÚMERO DO ÔNINUS"
              maxLength='10'
              name="numeroOnibus"
              type="text"
              required
            />

            <InputComRotulo
              texto="Código do Dispositivo"
              maxLength='20'
              name="codigoDispositivo"
              required
            />
          </Caixa>

          <BotaoForm texto="Finalizar" concluir={true}  url="/cadastroOnibus" />
        </FormContainer>
      </CorpoPagina>

    </Container>
  );
}
