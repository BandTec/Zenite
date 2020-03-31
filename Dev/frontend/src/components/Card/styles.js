import styled from 'styled-components';

export const Container = styled.div`
`;

const tamanhos = {
    1: "1 / 3",
    2: "2 / 4",
}

export const Card = styled.div`
    --claro: #CCEAFA;
    --suave: #5CB9E9; 
    --escuro: #0285C0;
    background: var(--${props => props.cor});

    border-radius: 6px;
    grid-row: ${props => tamanhos[props.tamanho]};
`;