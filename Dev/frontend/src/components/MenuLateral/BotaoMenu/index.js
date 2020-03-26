import React from 'react';
import { Link } from 'react-router-dom';

import Onibus from '../../../assets/icons/onibus.svg';
import Linha from '../../../assets/icons/linha.svg';
import Fiscal from '../../../assets/icons/fiscal.svg';
import Motorista from '../../../assets/icons/motorista.svg';
import Perfil from '../../../assets/icons/config.svg';
import Logout from '../../../assets/icons/logout.svg';
import LogoIcone from '../../../assets/logos/favicon4.png';

import { Botao, Icone, Texto } from './styles';

export default function BotaoMenu({ iconeNome, descricao, url, alt, ativo=false } ) {

  const iconesLib = {
    onibus: Onibus,
    linha: Linha,
    fiscal: Fiscal,
    motorista: Motorista,
    perfil: Perfil,
    logout: Logout,
    logo: LogoIcone
  }

  return (
    <Link to={url}>
      <Botao ativo={ativo}>
          <Icone src={iconesLib[iconeNome]} alt={alt} />
        <Texto>{descricao}</Texto>
      </Botao>
    </Link>
  );
}
