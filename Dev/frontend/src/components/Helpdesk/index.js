import React from 'react';

import { FundoBotao, Image } from './styles';
import Suporte from '../../assets/icons/suporte.png';

export default function Helpdesk() {
  
  return (  
    <FundoBotao>
      <a href="https://zenite.tomticket.com/chat/?id=EP46507&ac=2976448P08062020044839" target="_blank">
        <Image src={Suporte} alt="Icone Suporte"/>
      </a>
    </FundoBotao>
  );
}