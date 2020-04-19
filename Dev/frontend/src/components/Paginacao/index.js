import React from 'react';

import { Container } from './styles';
import TextoPaginacao from './TextoPaginacao';
import Botao from '../Botao';


export default function Paginacao() {
  return (
    <Container>
      <Botao descricao="Voltar" estiloEscuro={false} url="" />
        <TextoPaginacao paginaAtual="1" totalPaginas="10" />
      <Botao descricao="Próximo" estiloEscuro={false} url="" /> 
    </Container>
  );
}