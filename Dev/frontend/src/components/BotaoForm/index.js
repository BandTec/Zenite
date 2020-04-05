import React from 'react';

import { Link } from 'react-router-dom';
import { Container, Icone, Texto } from './styles';
// import Seta from "../../assets/icons/setaOval.png";
import Check from "../../assets/icons/check.png";

export default function BotaoForm({texto, url, ladoDireito = true, concluir = false, invisivel= false}) {
  return (
    <Link to={url}>
      <Container ladoDireito={ladoDireito} invisivel={invisivel}>
        <Texto>{texto}</Texto>
        <Icone src={Check} ladoDireito={ladoDireito} />
      </Container>
    </Link>
  );
}
