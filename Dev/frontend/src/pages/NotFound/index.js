import React from "react";
import Botao from "components/Botao";
import {useHistory} from "react-router-dom"
import { Container } from "./styles";

function NotFound() {
  const history = useHistory();
  const voltar = () => {
    history.goBack();
  }
  return (
    <Container>
      <h1>Página não encontrada</h1>
      <Botao descricao={"voltar para tela anterior"} estiloEscuro={true} onClick={voltar} />
    </Container>
  );
}

export default NotFound;
