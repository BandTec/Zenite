import React from 'react';

 import { Container } from './styles';
 import TipoDado from  './TipoDado';
 import DescricaoDado from './DescricaoDado';

export default function TituloDado({tipo, descricao}) {
  return (
    <Container>
      <TipoDado texto={tipo}/>
      <DescricaoDado texto={descricao}/>
    </Container>
  );
}
