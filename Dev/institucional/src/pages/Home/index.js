import React from 'react';
import { Container } from './styles';
import Menu from '../../components/Menu';
import Footer from "../../components/Footer";
import Header from "../../pages/Header";
import Sobre from "../../pages/Sobre";
import Produtos from "../../pages/Produtos";
import Time from "../../pages/Time";
import Contato from "../../pages/Contato";

export default function Home() {
  return (
    <Container>
      <Menu />
      <Header />
      <Sobre />
      <Produtos />
      <Time />
      <Contato />
      <Footer />
    </Container>
  );
}
