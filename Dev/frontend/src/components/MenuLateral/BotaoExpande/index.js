import React from "react";

import Onibus from "../../../assets/icons/onibus.svg";
import Linha from "../../../assets/icons/linha.svg";
import Fiscal from "../../../assets/icons/fiscal.svg";
import Motorista from "../../../assets/icons/motorista.svg";
import Perfil from "../../../assets/icons/config.svg";
import Logout from "../../../assets/icons/logout.svg";
import LogoIcone from "../../../assets/logos/favicon4.png";
import Gerente from "../../../assets/icons/adm1.svg";
import Adm from "../../../assets/icons/adm2.svg";
import Seta from "../../../assets/icons/chevron-down-solid.svg";

import {
  Botao,
  SetaIcone,
  Container,
  Icone,
  Texto,
  Nav,
} from "../BotaoMenu/styles";

export default function BotaoMenu({
  iconeNome,
  principal,
  btnEscondidos,
  alt,
}) {
  const iconesLib = {
    onibus: Onibus,
    linha: Linha,
    fiscal: Fiscal,
    motorista: Motorista,
    config: Perfil,
    logout: Logout,
    dashboard: LogoIcone,
    admin: Adm,
    gerente: Gerente,
  };

  const linkEstaAtivo = (match, location) => {
    const url = location.pathname.replace(/\/(\w*)/, "$1");
    const urlCaminho = url.replace(/(\w*)\/.*/, "$1");
    return urlCaminho === iconeNome;
  };

  return (
    <Container>
      <Botao>
        <Icone src={iconesLib[iconeNome]} alt={alt} />
        <Texto>
          {principal}
          <SetaIcone src={Seta} alt="seta" />
        </Texto>
      </Botao>
      {btnEscondidos.map((item) => (
        <Nav
          to={item.url}
          exact
          activeClassName="activeLink"
          isActive={linkEstaAtivo}
          key={item.texto}
        >
          <Botao>
            <Texto>{item.texto}</Texto>
          </Botao>
        </Nav>
      ))}
    </Container>
  );
}
