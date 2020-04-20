import React from "react";

import { Container, Rotulo, ComboBox, TextoAlerta } from "./styles";

export default function ComboBoxComRotulo(props) {
  const {
    texto,
    pequeno,
    invalido,
    textoAlerta,
    conteudoCombo,
    onchange,
    stateSelecionado
  } = props;

  return (
    <Container>
      <Rotulo>{texto}</Rotulo>
      {textoAlerta && <TextoAlerta>{textoAlerta}</TextoAlerta>}
      <ComboBox
        value={stateSelecionado}
        pequeno={pequeno}
        invalido={invalido}
        {...props}
        onChange={onchange}
      >
        {conteudoCombo.map((item) => (
          <option key={item.value} value={item.value}>
            {item.texto}
          </option>
        ))}
      </ComboBox>
      <p>{invalido}</p>
    </Container>
  );
}
