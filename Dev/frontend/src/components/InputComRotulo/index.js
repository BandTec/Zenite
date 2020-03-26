import React from 'react';

import { Container, Rotulo, Input } from './styles';

export default function InputComRotulo(props) {
  const {texto, pequeno} = props;

  return (
    <Container>
      <Rotulo>{texto}</Rotulo>
      <Input pequeno={pequeno} {...props} />
    </Container>
  );
}
