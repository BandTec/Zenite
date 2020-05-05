import React from 'react';

import { Container, Credits } from './styles';

export default function Footer() {
  const ano = new Date().getFullYear();
  return (
    <Container>
      <Credits>©{ano} - Órion Software . Todos os direitos reservados</Credits>
      <Credits>Design by ORION</Credits>
    </Container>
  );
}
