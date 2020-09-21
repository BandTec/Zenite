import styled from 'styled-components';

export const Container = styled.div`
  
`;

export const CaixaHorizontal = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: ${props => props.center ? "center" : "space-between"};
    align-items: center;
`;