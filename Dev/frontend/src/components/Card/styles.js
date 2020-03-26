import styled from 'styled-components';

export const Container = styled.div`
`;

export const Card = styled.div`
    --grande: 400px;
    --medio: 230px;
    --pequeno: 150px;

    --claro: #CCEAFA;
    --suave: #5CB9E9; 
    --escuro: #0285C0;

    width: 49%;
    height: var(--${props => props.tamanho});
    background: var(--${props => props.cor});
    margin-top: 20px;
`;