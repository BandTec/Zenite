import React from 'react';

import { Container } from './styles';



export default function TextoPaginacao( {paginaAtual, totalPaginas} ) {
  return (
    <Container>
        {paginaAtual} de {totalPaginas} páginas
    </Container>
  );
}
