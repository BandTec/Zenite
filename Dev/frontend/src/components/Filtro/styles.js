import styled from 'styled-components';

export const Container = styled.button`
  display: flex;
  height: 40px;
  /* width: 24%; */
  border-radius: 50px;
  margin-left: 25px;
  width: 200px;
  border: ${(props) => (props.selected ? "none" : "solid 2px #0B478D")};
  background: ${(props) =>
    props.selected
      ? "linear-gradient(253.32deg, #223F61 -19.51%, #0285C0 161.24%)"
      : "transparent"};
  /* background-color:${(props) => (props.selected ? "#223F61" : "#ffffff")}; */
  color: ${(props) => (props.selected ? "#ffffff" : "#223F61")};
  justify-content: center;
  align-items: center;
  /* border: solid 3px #223F61; */
  text-transform: uppercase;
  cursor: pointer;
`;
