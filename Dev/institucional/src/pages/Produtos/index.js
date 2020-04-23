import React from 'react';
import { Container } from './styles';

import Zenite from "../../assets/img/zenite.png";

export default function Header() {
  return (
    <Container id="produtos">
      <h4>Produtos</h4>
      <section>
        <div>
          <img src={Zenite} alt="Logo do software zenite." />

          <p>
            Nosso sistema de gerenciamento de transporte público para o estado
            de São Paulo.
          </p>

          <p>
            No momento oferecemos somente a versão desktop porém logo
            disponibilizaremos a versão mobile.
          </p>
        </div>

        {/* <img src={Phone} alt="Metade de um telefone" /> */}
      </section>
    </Container>
  );
};
