import React from "react";
import { useField } from "formik";
import { Container, Rotulo, Input, TextoAlerta, ErrorMessage } from "./styles";

export default function InputFormik(props) {
  const { texto, pequeno, invalido, textoAlerta, required, mask } = props;

  const [field, meta] = useField(props.name);
  const [didFocus, setDidFocus] = React.useState(false);

  const handleFocus = () => setDidFocus(true);

  const showFeedback =
    (!!didFocus && field?.value?.trim().length > 2) || !!meta.touched;

  return (
    <Container>
      <Rotulo>
        {texto} {required && "*"}
      </Rotulo>
      {textoAlerta && <TextoAlerta>{textoAlerta}</TextoAlerta>}
      <Input
        {...props}
        {...field}
        mask={mask}
        onFocus={handleFocus}
        feedback={showFeedback ? (!!meta.error ? 1 : 0) : 0}
        pequeno={pequeno}
        invalido={invalido}
      />
      {showFeedback
        ? meta.error && <ErrorMessage>{meta.error}</ErrorMessage>
        : null}
    </Container>
  );
}
