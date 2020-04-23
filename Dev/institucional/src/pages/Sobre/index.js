import React from 'react';
import { Container } from './styles';
import RobustezIcon from "../../assets/img/robustez.svg";
import TecnologiaIcon from '../../assets/img/tecnologias.svg';
import ProximidadeIcon from '../../assets/img/clientes.svg';

export default function Sobre() {
  return (
    <Container id="sobrenos">
      <h4>Sobre Nós</h4>

      <section>
        <div>
          <img src={RobustezIcon} alt="Ícone de um notebook com redes" />
          <h3>Robustez</h3>
          <p>
            Sistemas robustos e escaláveis para melhor atender os nossos
            clientes.
          </p>
        </div>

        <div>
          <img src={TecnologiaIcon} alt="Ícone de um notebook com redes" />
          <h3>Tecnologias Atuais</h3>
          <p>
            Utilizamos tecnologias e metodologias atuais visando oferecer o que
            á de melhor para o seu projeto.
          </p>
        </div>

        <div>
          <img src={ProximidadeIcon} alt="Ícone de um notebook com redes" />
          <h3>Proximidade</h3>
          <p>Trabalhamos junto ao cliente para garantir sua satisfação.</p>
        </div>
      </section>
    </Container>
  );
};
