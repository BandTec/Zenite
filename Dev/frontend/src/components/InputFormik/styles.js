import styled, {css} from "styled-components";
import InputMask from "react-input-mask";

export const Container = styled.div`
  margin-top: 36px;
`;

export const Input = styled(InputMask)`
  width: ${(props) => (props.pequeno ? 240 : 500)}px;
  width: ${(props) => props.tamanho}px;
  height: 40px;
  border-width: 1.5px;
  border-style: solid;
  border-color:  #C4C4C4;
  border-radius: 10px;
  padding: 11px 15px 11px 15px;
  /* margin-right: ${(props) => (props.pequeno ? 20 : 0)}px; */
  color: #282828;
  font-size: 15px;
  letter-spacing: 0.05em;
  transition: all ease-in-out 0.3s;

  :focus {
    border-color: #223F61;
  }

  ${props =>
    props.feedback &&
    css`
      border: 1px solid red !important;
    `}

  input:-moz-ui-invalid {
    box-shadow: none;
  }

  :-moz-ui-invalid {
    box-shadow: none;
  }
`;

export const Rotulo = styled.label`
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

export const ErrorMessage = styled.p`
    margin-top: 5px;
    font-size: 12px;
    color: red;
`;
