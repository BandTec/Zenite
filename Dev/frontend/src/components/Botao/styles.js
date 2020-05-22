import styled from "styled-components";

export const BotaoContainer = styled.button`
  background: ${(props) =>
    props.estiloEscuro
      ? "linear-gradient(253.32deg, #223F61 -19.51%, #0285C0 161.24%)"
      : "transparent"};
  color: ${(props) => (props.estiloEscuro ? "#FFFFFF" : "#0B478D")};
  text-transform: uppercase;
  font-weight: 700;

  border-radius: 50px;
  border: ${(props) => (props.estiloEscuro ? "none" : "solid 2px #0B478D")};

  height: 45px;
  width: ${(props) => props.tamanho}px;

  padding: 0px 35px;
  margin: 20px 0px;
`;
