import React from 'react';

import { Container, Icone, Texto } from './styles';
import Seta from "../../assets/icons/setaOval.png";
import Check from "../../assets/icons/check.png";

export default function BotaoForm({texto, acao, ladoDireito = true, concluir = false}) {
  return (
    <Container onClick={acao} ladoDireito={ladoDireito}>
      <Texto>{texto}</Texto>
      <Icone src={concluir == true ? Check : Seta} ladoDireito={ladoDireito} />
    </Container>
  );
}
