import React from 'react';

import { Container, Titulo, SubTitulo, Valor } from './styles';

function Quadrado({titulo, subTitulo, valor, cor}) {
  return (
    <Container>
        <Titulo cor={cor}>{titulo}</Titulo>
        <SubTitulo cor={cor}>{subTitulo}</SubTitulo>
        <Valor cor={cor}>{valor}</Valor>
    </Container>
  );
}

export default Quadrado;