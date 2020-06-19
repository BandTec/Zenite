import styled from 'styled-components';

export const Container = styled.div`
    padding: 20px;
    text-align: center;
`;

export const Titulo = styled.div`
    --claro: #ffffff;
    --suave: #223F61; 
    --escuro: #ffffff;
    font-size: 25px;
    color: var(--${props => props.cor});
`;

export const SubTitulo = styled.div`
    --claro: #ffffff;
    --suave: #223F61; 
    --escuro: #ffffff;
    color: var(--${props => props.cor});
`;

export const Valor = styled.div`
    --claro: #ffffff;
    --suave: #223F61; 
    --escuro: #ffffff;
    font-size: 40px;
    color: var(--${props => props.cor});
`;