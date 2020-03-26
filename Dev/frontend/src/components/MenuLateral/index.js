import React from 'react';
import { Link } from 'react-router-dom';

import {
  Container,
  Icone,
  MainMenu
} from './styles';

import Onibus from '../../assets/icons/onibus.svg';
import Linha from '../../assets/icons/linha.svg';
import Fiscal from '../../assets/icons/fiscal.svg';
import Motorista from '../../assets/icons/motorista.svg';
import Perfil from '../../assets/icons/config.svg';
import Logout from '../../assets/icons/logout.svg';
import Logo from '../../assets/logos/favicon1.png';
// import Icone from '../Icone';

export default function MenuLateral() {
  //const icones = ['Fiscal', 'Linha', 'Motorista', 'Onibus', 'Perfil']
  return (
    <Container>
        <MainMenu>
          <Link to="/dashboard">
            <img src={Logo} alt="Logo do Software Zenite"/>
          </Link>
          <Link to="/fiscal">
            <Icone src={Fiscal} title="Fiscal"/>
          </Link>
          <Icone src={Linha} title="Linha"/>
          <Icone src={Motorista} title="Motorista"/>
          <Icone src={Onibus} title="Ônibus"/>
          <Icone src={Perfil} title="Perfil"/>
        </MainMenu>
          <Icone src={Logout} title="Logout"/>
       
    </Container>
  );
}
