import React from "react";

import { Container } from "./styles";

export default function TextoPaginacao({
  paginaAtual,
  totalPaginas,
  totalItens,
}) {
  return (
    <Container>
      {paginaAtual} de {totalPaginas} páginas
    </Container>
  );
}
