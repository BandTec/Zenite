/* eslint react-hooks/exhaustive-deps: 0 */
import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

import BotaoMenu from "./BotaoMenu";
import BotaoExpande from "./BotaoExpande";
import { Container, MainMenu } from "./styles";

export default function MenuLateral(props) {
  const { pathname, state } = useLocation();
  const [nivel, setNivel] = useState(0);
  const gerenteConfig = [
    { texto: "Perfil", url: "/perfil" },
    { texto: "Alocar", url: "/alocacao" },
  ];

  const outrosConfig = [{ texto: "Perfil", url: "/perfil" }];

  let nivelStorage = localStorage.getItem("nivel");

  useEffect(() => {
    if (pathname === "/") {
      setNivel(0);
    } else {
      if (state) {
        setNivel(Number(state.nivel));
      } else if (nivelStorage) {
        nivelStorage = Number(nivelStorage);
        setNivel(nivelStorage);
      }
    }
  }, [pathname, nivelStorage]);

  function logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("nivel");
    localStorage.removeItem("nome");

    window.location = "/";
  }

  return (
    <>
      {nivel !== 0 && (
        <Container>
          <MainMenu>
            <BotaoMenu
              descricao="Início"
              iconeNome="dashboard"
              alt="Logo do Software Zenite"
              url={"/dashboard"}
            />

            {nivel < 4 && (
              <BotaoMenu
                descricao="Fiscal"
                iconeNome="fiscal"
                url={"/fiscal"}
              />
            )}

            {nivel !== 5 && (
              <BotaoExpande
                principal="Linha"
                btnEscondidos={[
                  { texto: "Linha", url: "/linha" },
                  { texto: "Parada", url: "/parada" },
                ]}
                iconeNome="linha"
              />
            )}

            {nivel !== 5 && (
              <BotaoMenu
                descricao="Motorista"
                iconeNome="motorista"
                url={"/motorista"}
              />
            )}

            {nivel !== 5 && (
              <BotaoMenu
                descricao="Ônibus"
                iconeNome="onibus"
                url={"/onibus"}
              />
            )}

            {nivel === 2 && (
              <BotaoMenu
                descricao="Admin"
                url={"/administrador"}
                iconeNome="admin"
              />
            )}

            {nivel !== 2 && (
              <BotaoExpande
                principal="Configuração"
                btnEscondidos={nivel === 3 ? gerenteConfig : outrosConfig}
                iconeNome="config"
              />
            )}

            {nivel <= 3 && (
              <BotaoMenu
                descricao="Gerente"
                url={"/gerente"}
                iconeNome="gerente"
              />
            )}
          </MainMenu>

          <BotaoMenu descricao="Sair" iconeNome="logout" onclick={logout} />
        </Container>
      )}
    </>
  );
}
