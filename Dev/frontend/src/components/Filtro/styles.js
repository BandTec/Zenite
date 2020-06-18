import styled from 'styled-components';

export const Container = styled.button`
  display: flex;
  height: 50px;
  width: 24%;
  background-color:${props => (props.selected ? '#223F61': '#ffffff')};
  color: ${props => (props.selected ? '#ffffff': '#223F61')};
  justify-content: center;
  align-items: center;
  border-radius: 5px;
  border: solid 3px #223F61;
  text-transform: uppercase;
  cursor: pointer;
`;
