import styled from "styled-components";

export const Container = styled.div``;

export const ComboBox = styled.select`
  width: ${(props) => (props.pequeno ? 240 : 500)}px;
  width: ${(props) => props.tamanho}px;
  height: 40px;
  border-width: 1.5px;
  border-style: solid;
  border-color: ${(props) => (props.invalido ? "red" : "#C4C4C4")};
  border-radius: 10px;
  padding: 0px 15px;
  /* margin-right: ${(props) => (props.pequeno ? 20 : 0)}px; */
  color: #282828;
  font-size: 15px;
  letter-spacing: 0.05em;
  transition: all ease-in-out .3s;

  :focus {
    border-color: ${(props) => (props.invalido ? "red" : "#223F61")};
  }
`;

export const Rotulo = styled.label`
  margin-top: 36px;
  display: block;
  font-size: 15px;
  letter-spacing: 0.05em;
  color: #223f61;
  margin-bottom: 10px;
  text-transform: uppercase;
`;

export const TextoAlerta = styled.p`
  font-size: 10px;
  font-weight: 600;
  letter-spacing: 0.05em;
  line-height: 12px;
  color: #282828;
  padding-bottom: 10px;
`;
