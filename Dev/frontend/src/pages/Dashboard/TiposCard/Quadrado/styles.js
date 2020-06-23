import styled from 'styled-components';

export const Container = styled.div`
    padding: 20px;
    text-align: center;
`;

export const Titulo = styled.h3`
    --claro: #ffffff;
    --suave: #223F61; 
    --escuro: #ffffff;
    font-size: 25px;
    font-weight: 700;
    color: var(--${props => props.cor});
`;

export const SubTitulo = styled.h3`
    --claro: #ffffff;
    --suave: #223F61; 
    --escuro: #ffffff;
    color: var(--${props => props.cor});
`;

export const Valor = styled.h3`
    --claro: #ffffff;
    --suave: #223F61; 
    --escuro: #ffffff;
    font-size: 40px;
    font-weight: 800;
    color: var(--${props => props.cor});
`;