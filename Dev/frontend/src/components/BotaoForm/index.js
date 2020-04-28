import React from "react";

import { Container, Icone, Texto } from "./styles";
// import Seta from "../../assets/icons/setaOval.png";
import Check from "../../assets/icons/check.png";

export default function BotaoForm({
  texto,
  ladoDireito = true,
  concluir = false,
  mudarPagina,
  criarJson,
}) {
  const clicar = () => {
    criarJson && criarJson();
    mudarPagina && mudarPagina(ladoDireito);
  };

  return (
    <Container ladoDireito={ladoDireito} onClick={clicar}>
      <Texto>{texto}</Texto>
      <Icone src={Check} ladoDireito={ladoDireito} />
    </Container>
  );
}
