import styled from 'styled-components';
import Phone from "../../assets/img/phone.png";
import Monitor from "../../assets/img/monitor1.png";

export const Container = styled.article`
  background: linear-gradient(180deg, #CCEAFA 0%, rgba(204, 234, 250, 0) 100%);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding: 50px 0px;
  
  section {
    background-image: ${`url(${Monitor}), url(${Phone})` };
    background-position: left center, right center;
    background-repeat: no-repeat;
    height: 400px;
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  div {
    width : 30%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  p {
    margin-top: 30px;
    text-align: center;
  }
`;
