import React from 'react';

import { Container, Tela, Corpo, Cabecalho, Row, Perfil } from './styles';
import Card from '../../components/Card';
import Titulo from '../../components/Titulo';
import Filtro from '../../components/Filtro';

var data = new Date()

export default function Dashboard() {
  return (
    <Container>
        
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
            <Card tamanho={1} cor='claro' />
            <Card cor='suave'/>
            <Card cor='escuro'/>
            <Card tamanho={2} cor='escuro'/>
          </Corpo>
        </Tela>
    </Container>
  );
}
