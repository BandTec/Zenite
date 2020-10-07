import React from "react";

import { Container, Icone, Texto } from "./styles";
// import Seta from "../../assets/icons/setaOval.png";
import Check from "../../../assets/icons/check.png";

export default function Button({
  texto,
  ladoDireito = true,
  onClick,
  type,
  disabled,
}) {
  return (
    <Container
      type={type}
      disabled={disabled}
      ladoDireito={ladoDireito}
      onClick={onClick}
    >
      <Texto>{texto}</Texto>
      <Icone src={Check} ladoDireito={ladoDireito} />
    </Container>
  );
}
