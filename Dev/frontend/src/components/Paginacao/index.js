import React from 'react';

import { Container } from './styles';
import TextoPaginacao from './TextoPaginacao';
import Botao from '../Botao';


export default function Paginacao({pgAtual, totalPg, voltar, proximo}) {
  const fim = totalPg - 1;
  return (
    <Container>
      <Botao descricao="Voltar" estiloEscuro={false} onClick={voltar} disabled={pgAtual === 0} />
      <TextoPaginacao paginaAtual={pgAtual} totalPaginas={totalPg} />
      <Botao descricao="Próximo" estiloEscuro={false} onClick={proximo} disabled={pgAtual === fim} />
    </Container>
  );
}