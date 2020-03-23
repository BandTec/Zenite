import React from 'react';

import { Container, ButtonTeste } from './styles';

export default function Button(props) {
  return (
    <Container>
      <ButtonTeste>{props.text}</ButtonTeste>
    </Container>
  );
}
