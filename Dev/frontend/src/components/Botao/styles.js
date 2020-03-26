import styled from 'styled-components';

export const BotaoContainer = styled.button`
    cursor: pointer;
    border-radius: 50px;
    border: ${props => props.estiloEscuro ? "none" : "solid 2px #0B478D"};
    width: 150px;
    height: 45px;
    background: ${props => props.estiloEscuro ?  
        "linear-gradient(253.32deg, #223F61 -19.51%, #0285C0 161.24%)"
        : "#FFFFFF"};
    color: ${props => props.estiloEscuro ? "#FFFFFF" : "#0B478D"};
    text-transform: uppercase;
    font-weight: 700;
`;
