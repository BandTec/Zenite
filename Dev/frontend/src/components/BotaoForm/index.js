import React from 'react';

import { Container, Icone, Texto } from './styles';
// import Seta from "../../assets/icons/setaOval.png";
import Check from "../../assets/icons/check.png";

export default function BotaoForm({texto, ladoDireito = true, concluir = false, invisivel= false, mudarPagina = () => {} }) {
  return (
    <Container ladoDireito={ladoDireito} invisivel={invisivel} 
      onClick={() => mudarPagina(ladoDireito)}>
      <Texto>{texto}</Texto>
      <Icone src={Check} ladoDireito={ladoDireito} />
    </Container>
  );
}
