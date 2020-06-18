import React from 'react';
import { Bar as BarTemplate } from 'react-chartjs-2';

import { iniciarData, options } from './config';
import { Container, Titulo, Grafico } from './styles';

function Bar({ titulo = "Gráfico", dados = { labels: [], data: [] } }) {

    const data = iniciarData(dados); 

    return (
      <Container>
          <Titulo>{titulo}</Titulo>
          <Grafico>
            <BarTemplate 
            data={data}
            options={options}
            />
          </Grafico>
      </Container>
  )
}

export default Bar;