import styled from "styled-components";
import Check from "../../assets/icons/check.png";

export const Container = styled.article`
  padding: 20px 50px;
  display: flex;
  flex-direction: column;
`;

export const Row = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: ${(props) => (props.start ? "flex-start" : "space-around")};
  width: 100%;
`;

export const Col = styled.div`
  width: 45%;
  padding-right: ${(props) => (props.start ? 20 : 0)}px;
`;

export const Nav = styled.div`
  background-color: #cceafa;
  padding: 30px;
  border-radius: 5px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin: 40px 0px;

  div {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-around;
    width: 50%;
  }

  select {
    background: transparent;
    font-size: 30px;
    color: #223f61;
    font-weight: 800;
    text-transform: uppercase;
    border-bottom: 4px solid #5cb9e9;
    -webkit-appearance: none;
    -moz-appearance: none;
    background: url(${Check}) no-repeat right;
    width: 230px;
  }
`;

export const InfoBlock = styled.div`
  background-color: #e1eef5;
  padding: 25px;
  margin: 30px 0px;
`;

export const InfoTitle = styled.h4`
  font-weight: 700;
  font-size: 21px;
  color: #0066ac;
`;

export const InfoDetalhe = styled.h5`
  font-weight: 600;
  font-size: 15px;
  line-height: 24px;
  color: #223f61;
`;

export const Titulo = styled.h1`
  font-size: 30px;
  color: #223f61;
  font-weight: 800;
  text-transform: uppercase;
`;

export const Subtitulo = styled.h2`
  font-size: 16px;
  color: #0066ac;
  letter-spacing: 0.05em;
  text-transform: uppercase;
`;

export const Texto = styled.h2`
  font-size: 16px;
  font-weight: 700;
  color: #0066ac;
  letter-spacing: 0.05em;
  text-transform: uppercase;
  /* padding-right: 20px; */
`;

export const Rotulo = styled.p`
  display: block;
  font-size: 15px;
  letter-spacing: 0.05em;
  color: #223f61;
  margin-bottom: 10px;
  text-transform: uppercase;
`;
