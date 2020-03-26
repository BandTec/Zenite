import styled from 'styled-components';

export const Container = styled.div`
    display: flex;
    flex-direction: row;
`;

export const CorpoPagina = styled.article`
    padding: 54px;
    background-color: #FFF;
    width: 100vw;    
`;

export const Titulo = styled.h1`
  font-size: 30px;
  color: #223F61;
  font-weight: 800;
  margin-bottom:15px;
  text-transform: uppercase;
`;

export const Subtitulo = styled.h2`
  font-size: 16px;
  color: #0066AC;
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
    justify-content: ${props => props.center ? "center" : "space-between"};
    align-items: center;
`;


