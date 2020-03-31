import React from 'react';

import { BotaoContainer } from './styles';

export default function Botao({ descricao, estiloEscuro = false, tamanho}) {
  return (
    <BotaoContainer estiloEscuro={estiloEscuro} tamanho={tamanho}>
      {descricao}
    </BotaoContainer>  
  );
}
