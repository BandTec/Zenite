import React from 'react';

import { Container } from './styles';
import TituloMenor from './TituloMenor';
import TituloMaior from './TituloMaior';

export default function Titulo( {textoMenor, textoMaior }) {
  return (
    <Container>
        <TituloMenor texto={textoMenor}/>
        <TituloMaior texto={textoMaior}/>
    </Container>
  );
}
