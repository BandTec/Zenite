import React from 'react';

import { Container, Tela, Corpo } from './styles';
import MenuLateral from '../../components/MenuLateral'
import Card from '../../components/Card';
import Titulo from '../../components/Titulo';

var data = new Date()

export default function Dashboard() {
  return (
    <Container>
        <MenuLateral />
        <Tela>
          <Titulo textoMenor={`Atualizado em ${data.getHours()}:${data.getMinutes()}`} textoMaior='Dashboard'/>
          <Corpo>
            <Card tamanho='grande' cor='claro' />
            <Card tamanho='medio' cor='suave'/>
            <Card tamanho='pequeno' cor='escuro'/>
            <Card tamanho='grande' cor='claro' />
          </Corpo>
        </Tela>
    </Container>
  );
}
