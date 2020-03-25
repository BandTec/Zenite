import React from 'react';

import { BotaoContainer } from './styles';

export default function Botao({ descricao, estiloEscuro = false}) {
  return (
    <BotaoContainer estiloEscuro={estiloEscuro}>
      {descricao}
    </BotaoContainer>  
  );
}
