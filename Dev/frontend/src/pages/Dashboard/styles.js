import styled from 'styled-components';

export const Container = styled.div`
  display:flex;
`;

export const Tela = styled.div`
  width:100%;
  margin: 50px 75px 50px 150px;
`;

export const Row = styled.div`
  display: flex;
  justify-content: space-between;
`
export const Perfil = styled.a`
  font-family: Open;
  color: #0066AC;
  font-size: 16px;
`;

export const Corpo = styled.section`
  display: grid;
  grid-template-columns: 1fr 1fr;
  margin-top: 20px;
`;

export const Cabecalho = styled.header`
  margin-top: 20px; 
  display: flex;
  justify-content: space-between;
`;
