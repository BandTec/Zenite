import React from 'react';

import { Container, Credits } from './styles';

export default function Footer() {
  const ano = 2020;
  return (
    <Container>
      <Credits>@Orion {ano} Todos os direitos reservados</Credits>
      <Credits>Design by ORION</Credits>
    </Container>
  );
}
