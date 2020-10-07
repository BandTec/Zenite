import styled from 'styled-components';

export const Container = styled.button`
  display: flex;
  flex-direction: ${(props) =>
    props.ladoDireito ? "row" : "row-reverse"};
  justify-content: space-around;
  align-items: center;
  cursor: pointer;

  :hover {
    opacity: 0.7;
  }
`;

export const Icone = styled.img`
  transform: ${props => props.ladoDireito ? "rotate(-90deg) " : "rotate(90deg)"};
`;

export const Texto = styled.p`
  
  font-size: 24px;
  font-weight: bold;
  color: #223F61;
  padding: 10px;
  text-transform: uppercase;
`;