import styled from "styled-components";

export const Select = styled.div`
  width: 45%;
`;

export const Container = styled.div`
  display: flex;
  flex-direction: row;
`;

export const Rotulo = styled.label`
  display: block;
  font-size: 15px;
  letter-spacing: 0.05em;
  color: #223f61;
  margin: 10px 0px;
  text-transform: uppercase;
`;



export const CorpoPagina = styled.article`
  padding: 40px;
  background-color: #fff;
  width: 100vw;
  height: 100%;
`;

export const Titulo = styled.h1`
  font-size: 30px;
  color: #223f61;
  font-weight: 800;
  margin-bottom: 15px;
  text-transform: uppercase;
`;

export const Subtitulo = styled.h2`
  font-size: 16px;
  color: #0066ac;
  letter-spacing: 0.05em;
  text-transform: uppercase;
`;

export const Caixa = styled.div`
  /* width: 100%; */
`;

export const FormContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;

  margin: 45px 0px;
`;

export const CaixaHorizontal = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: ${(props) => (props.center ? "center" : "space-between")};
  align-items: center;
  margin: 20px 0px;
`;
