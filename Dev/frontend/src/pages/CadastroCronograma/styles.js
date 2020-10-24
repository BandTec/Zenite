import styled from "styled-components";
import AsyncSelect from "react-select/async";

export const CustomSelect = styled(AsyncSelect)`
  width: 50%;
`;
export const Container = styled.div`
  display: flex;
  flex-direction: row;
`;

export const Rotulo = styled.label`
  margin-top: 25px;
  display: block;
  font-size: 15px;
  letter-spacing: 0.05em;
  color: #223f61;
  margin-bottom: 10px;
  text-transform: uppercase;
`;

export const Table = styled.table`
  width: 500px;
  margin-top: 35px;

  thead {
    td {
      color: #4f4f4f;
      text-transform: uppercase;
      letter-spacing: 1px;
    }
    tr {
      padding: 10px;
    }
  }
`;

export const Row = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 500px;
  margin: 25px 0px;
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

export const Caixa = styled.div``;

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
`;
