import styled from "styled-components";
import { NavLink } from "react-router-dom";

export const Nav = styled(NavLink)``;

export const Container = styled.div`
  & ${Nav} {
    display: none;
  }
  
  &:hover ${Nav} {
    display: block;
  }
`;

export const Botao = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 100%;
  padding: 15px;
  cursor: pointer;
  transition: all ease-in-out 0.5s;

  :hover {
    background-color: var(--azulMenuHover);
  }

  /* &:hover ~ ${Nav}, ${Nav} {
    display: block;
  } */
`;

export const Texto = styled.p`
  font-weight: 700;
  font-size: 14px;
  letter-spacing: 0.04em;
  color: #223f61;
  text-transform: uppercase;
  display: none;
`;

export const Icone = styled.img`
  width: 25px;
`;

export const SetaIcone = styled.img`
  width: 15px;
  padding-left: 5px;
`;
