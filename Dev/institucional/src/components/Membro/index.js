import React from 'react';

import { Container } from './styles';
import Marte from '../../assets/img/marte.png';
import Cometa from "../../assets/img/comet.png";
import Telescopio from "../../assets/img/tele.png";
import Venus from "../../assets/img/venus.png";
import Foguete from "../../assets/img/space.png";
import Astronauta from "../../assets/img/astronaut.png";
import Rover from "../../assets/img/lander.png";
import Github from "../../assets/img/github.svg";
import Linkedin from "../../assets/img/linkedin.svg";

export default function Membro({informacao}) {
  const {nome, cargo, github, linkedin, img} = informacao;
  
  const imagens = {
    marte: Marte,
    cometa: Cometa,
    telescopio: Telescopio,
    venus: Venus,
    foguete: Foguete,
    astronauta: Astronauta,
    rover: Rover,
  }
  return (
    <Container>
      <img src={imagens[img]} alt={img} />
      <h5>{nome}</h5>
      <p>{cargo}</p>

      <div>
      {github &&   
        <a href={github}>
          <img src={Github} alt="Github" />
        </a>
      }
      { linkedin &&
        <a href={linkedin}>
          <img src={Linkedin} alt="Linkedin" />
        </a>
      }
      </div>
    </Container>
  );
}
