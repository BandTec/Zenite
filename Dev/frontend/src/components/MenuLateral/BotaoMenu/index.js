import React from "react";
import { NavLink } from "react-router-dom";

import Onibus from "../../../assets/icons/onibus.svg";
import Linha from "../../../assets/icons/linha.svg";
import Fiscal from "../../../assets/icons/fiscal.svg";
import Motorista from "../../../assets/icons/motorista.svg";
import Perfil from "../../../assets/icons/config.svg";
import Logout from "../../../assets/icons/logout.svg";
import LogoIcone from "../../../assets/logos/favicon4.png";
import Gerente from "../../../assets/icons/adm1.svg";
import Adm from "../../../assets/icons/adm2.svg";

import { Botao, Icone, Texto } from "./styles";

export default function BotaoExpande({ iconeNome, descricao, url, alt, onclick }) {
  const iconesLib = {
    onibus: Onibus,
    linha: Linha,
    fiscal: Fiscal,
    motorista: Motorista,
    perfil: Perfil,
    logout: Logout,
    dashboard: LogoIcone,
    admin: Adm,
    gerente: Gerente,
    viagem: Onibus,
  };

  const linkEstaAtivo = (match, location) => {
    const url = location.pathname.replace(/\/(\w*)/, "$1");
    const urlCaminho = url.replace(/(\w*)\/.*/, "$1");
    return urlCaminho === iconeNome;
  };

  return url ? (
    <NavLink
      to={url}
      exact
      activeClassName="activeLink"
      isActive={linkEstaAtivo}
    >
      <Botao>
        <Icone src={iconesLib[iconeNome]} alt={alt} />
        <Texto>{descricao}</Texto>
      </Botao>
    </NavLink>
  ) : (
    <Botao onClick={onclick}>
      <Icone src={iconesLib[iconeNome]} alt={alt} />
      <Texto>{descricao}</Texto>
    </Botao>
  );
}
