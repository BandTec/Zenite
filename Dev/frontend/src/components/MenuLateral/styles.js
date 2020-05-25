import styled from "styled-components";
import { Texto } from "./BotaoMenu/styles";

export const Container = styled.div`
  position: fixed;
  background-color: var(--azulMenu);
  height: 100vh;
  width: 70px;
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  justify-content: space-between;
  padding: 30px 0px;
  position: fixed;
  z-index: 10;

  :hover {
    width: 230px;
  }

  :hover ${Texto} {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    padding-left: 25px;
  }
`;

export const MainMenu = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 100%;
`;
