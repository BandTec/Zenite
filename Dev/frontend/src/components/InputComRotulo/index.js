import React from 'react';

import { Container, Rotulo, Input, TextoAlerta } from './styles';

export default function InputComRotulo(props) {
  const {texto, pequeno, invalido, textoAlerta} = props;

  return (
    props.required ?
    (<Container>
      <Rotulo>{texto} *</Rotulo>
      {textoAlerta && <TextoAlerta>{textoAlerta}</TextoAlerta>}
      <Input pequeno={pequeno} invalido={invalido} {...props} />
      <p>{invalido}</p>
    </Container>
    ):
    (<Container>
      <Rotulo>{texto}</Rotulo>
      {textoAlerta && <TextoAlerta>{textoAlerta}</TextoAlerta>}
      <Input pequeno={pequeno} invalido={invalido} {...props} />
      <p>{invalido}</p>
    </Container>
    )
  );
}
