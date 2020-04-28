import React from 'react';
import { Container } from './styles';
import Logo from "../../assets/img/logo.png";

export default function Header() {
  return (
    <Container id="header">
      <img src={Logo} alt="Logo" />
      <h1>Software de outro mundo</h1>
    </Container>
  );
};
