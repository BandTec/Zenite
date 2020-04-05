import styled from 'styled-components';

export const Container = styled.div`
`;

export const Tela = styled.div`
     
    padding: 40px;
`;

export const Row = styled.div`
  display: flex;
  justify-content: space-between;
`
export const Perfil = styled.a`
  color: #0066AC;
  font-size: 16px;
`;

export const Corpo = styled.section`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(3, 20vh);
  grid-gap: 15px;
`;

export const Cabecalho = styled.header`
  margin: 20px 0px; 
  display: flex;
  justify-content: space-between;
`;
