import React from "react";
import { Field } from "formik";
import { Container, Rotulo, ComboBox, TextoAlerta } from "./styles";

export default function ComboBoxFormik(props) {
  const { texto, pequeno, conteudoCombo, name } = props;
  return (
    <Container>
      {texto && <Rotulo>{texto}</Rotulo>}
      <Field as={ComboBox} name={name} {...props}>
        <option value="0" disabled selected>
          Selecione um motorista
        </option>
        {conteudoCombo.map((item, index) => (
          <option key={index + " " + item.value} value={item.value}>
            {item.texto}
          </option>
        ))}
      </Field>
    </Container>
  );
}
