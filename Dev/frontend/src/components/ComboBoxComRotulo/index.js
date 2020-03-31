import React from 'react';

import { Container, Rotulo, ComboBox, TextoAlerta } from './styles';

export default function ComboBoxComRotulo(props) {
  const {texto, pequeno, invalido, textoAlerta, conteudoCombo} = props;

  return (
    <Container>
      <Rotulo>{texto}</Rotulo>
      {textoAlerta && <TextoAlerta>{textoAlerta}</TextoAlerta>}
      <ComboBox pequeno={pequeno} invalido={invalido} {...props}>
          
          {conteudoCombo.map(item =>  (
              <option key={item.value} value={item.value}>{item.texto}</option>
            )
          )}

      </ComboBox>
      <p>{invalido}</p>
    </Container>
  );
}