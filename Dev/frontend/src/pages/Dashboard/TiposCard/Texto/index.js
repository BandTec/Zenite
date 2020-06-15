import React from 'react';

import { Container, Titulo, Valor } from './styles';

function Texto({ titulo = '', valor = ''}) {
  return (
    <Container>
        <Titulo>{titulo}</Titulo>
        <Valor>{valor}</Valor>
    </Container>
  )
}

export default Texto;