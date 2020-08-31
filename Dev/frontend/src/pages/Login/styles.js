import styled from "styled-components";
import Img from "assets/img/loginbg.png";

export const Container = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  
  flex-wrap: wrap;
  height: 100vh;
`;

export const Background = styled.div`
  background: url(${Img}) center right,
    linear-gradient(134.1deg, #a1cfe7 42.24%, #5cb9e9 82.32%);
  background-size: contain;
  background-repeat: no-repeat;
  -webkit-font-smoothing: antialiased !important;
  width: 50%;
  height: 100%;
  @media (max-width: 800px) {
    width: 100%;
    order: 1;
    height: 20%;
    background-size: cover;
  }
`;

export const Form = styled.form`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  width: 50%;
  height: 100%;
  background: #fff;
  @media (max-width: 800px) {
    width: 100%;
    order: 2;
    height: 70%;
  }
`;

export const LogoZenite = styled.img`
  width: 150px;

  margin-bottom: 16px;
`;

export const RecuperarSenha = styled.a`
  margin-left: 21px;

  color: #0285c0;
`;

export const OpcoesAdicionais = styled.div`
  flex-direction: row;
`;
