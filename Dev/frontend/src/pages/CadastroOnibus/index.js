import React from 'react';
import { Link } from 'react-router-dom';

import { Container, CorpoPagina, FormContainer, Titulo, Subtitulo, Caixa } from './styles';
import BotaoForm from '../../components/BotaoForm';
import InputComRotulo from '../../components/InputComRotulo';

export default function CadastroOnibus() {
  return (
    <Container>
      <CorpoPagina>
        <FormContainer>
          <Link to="/onibus">
            <BotaoForm texto="VOLTAR" ladoDireito={false} />
          </Link>

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
          <Link to="/onibus">
            <BotaoForm texto="Finalizar" concluir={true} />
          </Link>
        </FormContainer>
      </CorpoPagina>
    </Container>
  );
}
