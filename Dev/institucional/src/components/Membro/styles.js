import styled from "styled-components";

export const Container = styled.div`
  background-color: #2A3180;
  width: 20%;
  height: 330px;
  margin: 25px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  img {
    width: 120px;
    margin-bottom: 20px;
  }

  h5, p {
    color: #fff;
  }

  p {
    font-style: italic;
  }

  section {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
  }

  div img {
    width: 24px;
    margin: 10px;
  }

  div {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
  }
`;
