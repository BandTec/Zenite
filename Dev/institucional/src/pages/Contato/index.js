import React from 'react';
import { Container, Button } from './styles';
import Mapa from '../../assets/img/map.png';

export default function Header() {
  return (
    <Container id="contato">
      <h4>Contato</h4>

      <section>
        <img src={Mapa} alt="Imagem do Mapa" />

        <form>
          <label>Nome Completo</label>
          <input type="text" required />

          <label>Email</label>
          <input type="email" required />

          <label>Telefone</label>
          <input type="text" required />

          <label>Mensagem</label>
          <textarea />

          <div>
            <Button principal>Enviar</Button>

            <Button>Limpar</Button>
          </div>
        </form>
      </section>
    </Container>
  );
};
