import styled from 'styled-components';

export const Container = styled.div`
  margin-top: 36px;
`;

export const Input = styled.input`
    width: ${props => props.pequeno ? 230 : 500}px;
    height: 40px;
    border: 1.5px solid #C4C4C4;
    border-radius: 10px;
    padding: 11px 15px 11px 15px;
    color: #282828;
    font-size: 15px;
    letter-spacing: 0.05em;
`

export const Rotulo = styled.label`
  display: block;
  font-size: 15px;
  letter-spacing: 0.05em;
  color: #223F61;
  margin-bottom: 10px;
`;

