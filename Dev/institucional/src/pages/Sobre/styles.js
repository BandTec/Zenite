import styled from 'styled-components';

export const Container = styled.article`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding: 5% 0px;
  
  section {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  }
  
  img {
    width: 120px;
    margin: 20px 0px;
  }

  div {
    width: 20%;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 50px;
    height: 400px;
  }

  p {
    text-align: center;
  }
`;


