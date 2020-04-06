import React from 'react';

import { Container, CorpoPagina, FormContainer, Titulo, Subtitulo, Caixa } from './styles';
import BotaoForm from '../../components/BotaoForm';
import InputComRotulo from '../../components/InputComRotulo';

export default function CadastroOnibus() {
  return (
    <Container>
      <CorpoPagina>
        <FormContainer>
          <BotaoForm texto="VOLTAR" url="/onibus" ladoDireito={false} />

          <Caixa>
            <Subtitulo>CADASTRO DO ÔNIBUS</Subtitulo>
            <Titulo>Dados</Titulo>

            <InputComRotulo
              texto="NÚMERO DO ÔNINUS"
              maxLength="10"
              name="numeroOnibus"
              type="text"
              required
            />

            <InputComRotulo
              texto="Código do Dispositivo"
              maxLength="20"
              name="codigoDispositivo"
              required
            />
          </Caixa>

          <BotaoForm texto="Finalizar" concluir={true} url="/onibus/cadastro" />
        </FormContainer>
      </CorpoPagina>
    </Container>
  );
}
