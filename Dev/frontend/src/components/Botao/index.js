import React from 'react';
import { Link } from 'react-router-dom';

import { BotaoContainer } from './styles';

export default function Botao(props) {
  const { descricao, estiloEscuro = false, tamanho, url} = props;
  return url ? (
    <Link to={url}>
      <BotaoContainer estiloEscuro={estiloEscuro} tamanho={tamanho}>
        {descricao}
      </BotaoContainer>
    </Link>
  ) : (
    <BotaoContainer estiloEscuro={estiloEscuro} tamanho={tamanho} {...props}>
      {descricao}
    </BotaoContainer>
  );
}
