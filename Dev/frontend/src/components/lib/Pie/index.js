import React from 'react';
import { Pie as PieTemplate } from 'react-chartjs-2';

import { iniciarData } from './config';
import { Container, Titulo, Grafico } from './styles';


function Pie({ titulo = "Gráfico", dados = { labels: [], data: [] } }) {

  const data = iniciarData(dados); 

  return (
    <Container>
        <Titulo>{titulo}</Titulo>
        <Grafico>
          <PieTemplate 
          data={data} 
          options={{ maintainAspectRatio: false }}/>
        </Grafico>
    </Container>
  )

}

export default Pie;