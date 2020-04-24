import React from "react";
import { Navbar, ListItens } from "./styles";
import Logo from "../../assets/img/logoIcon.png";

export default function Home() {

  return (
    <Navbar id="myHeader">
      <div>
        <img src={Logo} alt="Logo" />
      </div>

      <div>
        <ListItens>
          <li>
            <a href="#header">INÍCIO</a>
          </li>
          <li>
            <a href="#sobrenos">SOBRE NÓS</a>
          </li>
          <li>
            <a href="#produtos">PRODUTOS</a>
          </li>
          <li>
            <a href="#time">TIME</a>
          </li>
          <li>
            <a href="#contato">CONTATO</a>
          </li>
          <li>
            <a href="www.zenite-azurewebsites.new">ZÊNITE</a>
          </li>
        </ListItens>
      </div>
    </Navbar>
  );
}
