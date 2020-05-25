import React from 'react';

import { useLocation } from 'react-router-dom';

import {
  Container,
  MainMenu
} from './styles';

import BotaoMenu from './BotaoMenu';

export default function MenuLateral(props) {
  const {pathname} = useLocation();
  const mostrar = pathname === "/" ? false : true;

  function logout() {
    localStorage.removeItem("token");

    window.location ="/";
  }

  return (
    <>
      {mostrar && (
        <Container>
          <MainMenu>
            <BotaoMenu
              descricao="Início"
              iconeNome="dashboard"
              alt="Logo do Software Zenite"
              url={"/dashboard"}
            />

            <BotaoMenu descricao="Fiscal" iconeNome="fiscal" url={"/fiscal"} />

            <BotaoMenu descricao="Linha" iconeNome="linha" url={"/linha"} />

            <BotaoMenu
              descricao="Motorista"
              iconeNome="motorista"
              url={"/motorista"}
            />

            <BotaoMenu descricao="Ônibus" iconeNome="onibus" url={"/onibus"} />

            <BotaoMenu descricao="Perfil" url={"/perfil"} iconeNome="perfil" />

            <BotaoMenu
              descricao="Admin"
              url={"/administrador"}
              iconeNome="admin"
            />

            <BotaoMenu
              descricao="Gerente"
              url={"/gerente"}
              iconeNome="admin"
            />
          </MainMenu>

          <BotaoMenu descricao="Sair" iconeNome="logout" onclick={logout} />
        </Container>
      )}
    </>
  );
}
