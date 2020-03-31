import React from 'react';
import { Link } from 'react-router-dom';

import { BotaoContainer } from './styles';

export default function Botao({ descricao, estiloEscuro = false, tamanho, url}) {
  return (
    <Link to={url}>
      <BotaoContainer estiloEscuro={estiloEscuro} tamanho={tamanho}>
        {descricao}
      </BotaoContainer>  
    </Link>
  );
}
