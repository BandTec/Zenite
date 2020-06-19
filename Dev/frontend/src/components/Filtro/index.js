import React from 'react';

import { Container } from './styles';

export default function Filtro({titulo, selected, handlePage}) {
  return (
    <Container selected={selected} onClick={() => handlePage(titulo)}>
        {titulo}
    </Container>
  );
}
