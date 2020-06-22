import styled from 'styled-components';

export const Container = styled.div`
    display: flex;
    width: 100%; height: 100%;
    padding: 0 5%;
    align-items: center;
    flex-direction: column;
    background-color: #CCEAFA;
    border-radius: 5px;
`;

export const Titulo = styled.h3`
    display: flex;
    width: 100%; height: 20%;
    color: #0066AC;
    align-items: center;
    font-size: 20px;
    font-weight: bold;
    text-transform: uppercase;
`;

export const Grafico = styled.div`
    width: 100%; height: 75%;
    padding: 10px;
`;