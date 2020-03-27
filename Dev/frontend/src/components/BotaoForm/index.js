import React from 'react';

import { Link } from 'react-router-dom';
import { Container, Icone, Texto } from './styles';
import Seta from "../../assets/icons/setaOval.png";
import Check from "../../assets/icons/check.png";

export default function BotaoForm({texto, url, ladoDireito = true, concluir = false}) {
  return (
    <Link to={url}>
      <Container ladoDireito={ladoDireito}>
        <Texto>{texto}</Texto>
        <Icone src={concluir == true ? Check : Seta} ladoDireito={ladoDireito} />
      </Container>
    </Link>
  );
}
