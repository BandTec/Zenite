import React from 'react';

import { Container, Texto, Bolinha, Linha, Caixa} from './styles';

export default function StatusPage({ ativo, texto, temProximoPasso}) {
  return (
    <Container>
        <Texto>{texto}</Texto> 
        
      <Caixa>
        <Bolinha ativo={ativo}/>

        {temProximoPasso && <Linha />}
      </Caixa>
    </Container>
  );
    
}
