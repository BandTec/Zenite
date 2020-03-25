import React from 'react';

import { Container } from './styles';

export default function Icone({ nome }) {
  return (
    <Container>
        <img src={nome} alt="aaaaa"/>
    </Container>
  );
}
