import styled from 'styled-components';

export const Button = styled.button`
  background-color: ${(props) => (props.principal ? "#2A3180" : "#fff")};
  border: 2.5px solid #2a3180;
  color: ${(props) => (props.principal ? "#fff" : "#2A3180")};
  font-size: 0.9em;
  letter-spacing: 0.09em;
  border-radius: 4px;
  padding: 10px 17px;
  font-weight: 700;
  width: 45%;
  transition: all ease-in-out .3s;

  :hover {
    background-color: #161C5B;
    border: 2.5px solid #161C5B;
    color: #fff;
  }
`;

export const Container = styled.article`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  section {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    margin: 50px 0px;
  }

  label {
    color: #161C5B;
    display: block;
    margin: 10px 0px;
    font-size: 0.9em;
    font-weight: 600;
    letter-spacing: 0.09em;
  }

  input,
  textarea {
    width: 100%;
    padding: 10px 17px;
    border-radius: 4px;
    background: #efefef;
  }

  form {
    width: 400px;
  }

  div {
    margin: 25px 0px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
`;
