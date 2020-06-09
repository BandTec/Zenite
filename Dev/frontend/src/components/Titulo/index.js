import React from 'react';

import { Container, Total, Row } from "./styles";
import TituloMenor from './TituloMenor';
import TituloMaior from './TituloMaior';

export default function Titulo( {textoMenor, textoMaior, totalItens }) {
  return (
    <Container>
      <TituloMenor texto={textoMenor} />
      <Row>
        <TituloMaior texto={textoMaior} />
        { totalItens && <Total>{totalItens}</Total>}
      </Row>
    </Container>
  );
}
