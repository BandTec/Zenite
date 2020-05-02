import React from 'react';
import { NavLink } from 'react-router-dom';

import Onibus from '../../../assets/icons/onibus.svg';
import Linha from '../../../assets/icons/linha.svg';
import Fiscal from '../../../assets/icons/fiscal.svg';
import Motorista from '../../../assets/icons/motorista.svg';
import Perfil from '../../../assets/icons/config.svg';
import Logout from '../../../assets/icons/logout.svg';
import LogoIcone from '../../../assets/logos/favicon4.png';
import Adm from "../../../assets/icons/adm1.svg";

import { Botao, Icone, Texto } from './styles';

export default function BotaoMenu({
  iconeNome,
  descricao,
  url,
  alt,
  ativo = false,
}) {
  const iconesLib = {
    onibus: Onibus,
    linha: Linha,
    fiscal: Fiscal,
    motorista: Motorista,
    perfil: Perfil,
    logout: Logout,
    dashboard: LogoIcone,
    admin: Adm
  };

  const linkEstaAtivo = (match, location) => {
    const url = location.pathname.replace(/\/(\w*)/, "$1");
    const urlCaminho = url.replace(/(\w*)\/.*/, "$1");
    return urlCaminho === iconeNome;
  };

  return (
    <NavLink
      to={url}
      exact
      activeClassName="activeLink"
      isActive={linkEstaAtivo}
    >
      <Botao ativo={ativo}>
        <Icone src={iconesLib[iconeNome]} alt={alt} />
        <Texto>{descricao}</Texto>
      </Botao>
    </NavLink>
  );
}
