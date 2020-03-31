import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

export const Texto = styled.p`
  color: #223F61;
  font-weight: bold;
  font-size: 13px; /* 16 */
  margin-bottom: 10px; /* 16 */
  margin-left: -15px;
`;

export const Bolinha = styled.div`
  width: 30px; /* 35 */
  height: 30px;  /* 35 */
  border-radius: 50px;
  border: 5px solid #223F61;
  background-color: ${props => props.ativo == true ? "#223F61" : "#FFF"};
`;

export const Linha = styled.div`
    background-color: #223F61;
    width: 200px; /* 223 */
    height: 4px;
`;

export const Caixa = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
`;

