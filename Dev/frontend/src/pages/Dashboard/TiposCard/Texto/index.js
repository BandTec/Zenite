import React from 'react';

import { Container, Titulo, Valor } from './styles';

function Texto({ titulo = '', valor = '', children}) {
  return (
    <Container>
        <Titulo>{titulo}</Titulo>
        <Valor>{valor || children}</Valor>
    </Container>
  )
}

export default Texto;