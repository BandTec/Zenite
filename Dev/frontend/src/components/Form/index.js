import React from 'react';
import { Container } from './styles';

export default function Form({children}) {
  return (
      <Container>
          Tela de Login
          {children}
      </Container>
  );
}
