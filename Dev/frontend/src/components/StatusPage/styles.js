import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

export const Texto = styled.p`
  color: #223F61;
  font-weight: bold;
  font-size: 16px;
  margin-bottom:16px;
  margin-left: -15px;
`;

export const Bolinha = styled.div`
  width: 35px;
  height: 35px;
  border-radius: 50px;
  border: 5px solid #223F61;
  background-color: ${props => props.ativo == true ? "#223F61" : "#FFF"};
`;

export const Linha = styled.div`
    background-color: #223F61;
    width: 223px;
    height: 4px;
`;

export const Caixa = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
`;

