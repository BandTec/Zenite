import React from 'react';
import { Container } from './styles';
import Membro from '../../components/Membro';

export default function Header() {
  const alex = {
    nome: "Alex Buarque",
    cargo: "Desenvolvedor",
    github: "https://github.com/alexbuarque",
    linkedin: "https://github.com/alexbuarque",
    img: "rover",
  };

  const fernanda = {
    nome: "Fernanda Esteves",
    cargo: "Desenvolvedor",
    github: "https://github.com/esteves-esta",
    linkedin: "https://github.com/esteves-esta",
    img: "cometa",
  };

  const joao = {
    nome: "João Soares",
    cargo: "Desenvolvedor",
    github: "https://github.com/jPedroSoares",
    linkedin: "https://github.com/jPedroSoares",
    img: "marte",
  };

  const lais = {
    nome: "Lais Silva",
    cargo: "Desenvolvedor",
    github: "https://github.com/Laissilvaa",
    linkedin: "https://github.com/Laissilvaa",
    img: "telescopio",
  };

  const raissa = {
    nome: "Raissa Arantes",
    cargo: "Desenvolvedor",
    github: "https://github.com/Rayssawoods",
    linkedin: "https://github.com/Rayssawoods",
    img: "venus",
  };

  const vitor = {
    nome: "Vitor Silva",
    cargo: "Desenvolvedor",
    github: "https://github.com/vitorsilv",
    linkedin: "https://github.com/vitorsilv",
    img: "foguete",
  };

  const novo = {
    nome: "Você",
    cargo: "Estamos contratando!",
    img: "astronauta",
  };

  return (
    <Container id="time">
      <h4>Time</h4>

      <section>
        <Membro informacao={alex} />

        <Membro informacao={fernanda} />

        <Membro informacao={joao} />

        <Membro informacao={lais} />

        <Membro informacao={raissa} />

        <Membro informacao={vitor} />

        <Membro informacao={novo} />
      </section>
    </Container>
  );
};
