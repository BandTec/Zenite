import styled from "styled-components";

export const Container = styled.div`
  height: 100vh;
  width: 100%;
  padding: 20px 50px;
`;

export const Detalhes = styled.div`
  width: 100%;
  background-color: #cceafa;
  padding: 30px;
  display: flex;
  border-radius: 5px;
  margin: 20px 0px;

  div {
    margin-right: 25px;
    h5 {
      text-transform: uppercase;
      font-size: 18px;
      font-weight: bold;
      letter-spacing: 1px;
      color: #223f61;
    }

    p {
      font-size: 16px;
    }
  }
`;

export const Row = styled.div`
  width: 100%;
  margin-top: 20px;
`;

export const Acoes = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
`;
