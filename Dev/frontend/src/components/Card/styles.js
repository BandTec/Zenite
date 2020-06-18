import styled from 'styled-components';

export const Container = styled.div`
`;

export const Card = styled.div`
    --claro: #0066AC;
    --suave: #CCEAFA; 
    --escuro: #223F61;
    background: var(--${props => props.cor});

    border-radius: 6px;
    grid-row: ${props => props.row};
    grid-column: ${props => props.column};
`;