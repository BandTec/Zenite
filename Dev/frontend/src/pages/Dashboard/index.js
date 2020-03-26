import React from 'react';

import { Container, Tela, Corpo, Cabecalho, Row, Perfil } from './styles';
import MenuLateral from '../../components/MenuLateral'
import Card from '../../components/Card';
import Titulo from '../../components/Titulo';
import Filtro from '../../components/Filtro';

var data = new Date()

export default function Dashboard() {
  return (
    <Container>
        <MenuLateral />
        <Tela>
          <Row>
            <Titulo textoMenor={`Atualizado em ${data.getHours()}:${data.getMinutes()}`} textoMaior='Dashboard'/>
            <Perfil>
              Olá, João Soares
            </Perfil>
          </Row>
          <Cabecalho>
            <Filtro titulo='Linha de ônibus'/>
            <Filtro titulo='Ponto Final'/>
            <Filtro titulo='Periodo'/>
            <Filtro titulo='Fiscal/Motorista'/>
          </Cabecalho>
          <Corpo>
            <Card tamanho='grande' cor='claro' />
            <Card tamanho='medio' cor='suave'/>
            <Card tamanho='pequeno' cor='escuro'/>
          </Corpo>
        </Tela>
    </Container>
  );
}
