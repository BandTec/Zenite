import styled from 'styled-components';

export const Botao = styled.a`
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 100%;
  padding: 20px;
  cursor:pointer; 
  background-color: ${props => props.ativo ? "var(--azulMenuHover)" : "transparent"};

  :hover {
    background-color: var(--azulMenuHover);
  }
`;

export const Texto = styled.p`
  font-weight: 700;
  font-size: 16px;
  letter-spacing: 0.05em;
  color: #223F61;
  text-transform: uppercase;
  display: none;
`;

export const Icone = styled.img`
  width: 30px;
`