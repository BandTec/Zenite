import React from 'react';
import { Container } from './styles';
import Membro from '../../components/Membro';

export default function Header() {
  const alex = {
    nome: "Alex Buarque",
    cargo: "Desenvolvedor",
    github: "https://github.com/alexbuarque",
    linkedin: "https://www.linkedin.com/in/alex-buarque-118945171/",
    img: "rover",
  };

  const fabi = {
    nome: "Fabí Canedo",
    cargo: "Desenvolvedor",
    github: "https://github.com/Fabicaneyu",
    linkedin: "https://www.linkedin.com/in/fabicanedo/",
    img: "astronauta",
  };

  const fernanda = {
    nome: "Fernanda Esteves",
    cargo: "Desenvolvedor",
    github: "https://github.com/esteves-esta",
    linkedin: "https://www.linkedin.com/in/fernanda-e-48086a182/",
    img: "cometa",
  };

  const joao = {
    nome: "João Soares",
    cargo: "Desenvolvedor",
    github: "https://github.com/jPedroSoares",
    linkedin: "https://www.linkedin.com/in/jpedrosoares1/",
    img: "marte",
  };

  const lais = {
    nome: "Lais Silva",
    cargo: "Desenvolvedor",
    github: "https://github.com/Laissilvaa",
    linkedin: "https://www.linkedin.com/in/laissilvaa/",
    img: "telescopio",
  };

  const raissa = {
    nome: "Raissa Arantes",
    cargo: "Desenvolvedor",
    github: "https://github.com/Rayssawoods",
    linkedin: "https://www.linkedin.com/in/raissa-arantes-a49264118/",
    img: "venus",
  };

  const vitor = {
    nome: "Vitor Silva",
    cargo: "Desenvolvedor",
    github: "https://github.com/vitorsilv",
    linkedin: "https://www.linkedin.com/in/vitorsilv/",
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

        <Membro informacao={fabi} />

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
