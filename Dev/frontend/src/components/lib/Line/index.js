import React from 'react';
import { Line as LineTemplate } from 'react-chartjs-2';

import { iniciarData, options } from './config';
import { Container, Titulo, Grafico } from './styles';


function Line({ titulo = "Gráfico", dados = { labels: [], data: [] } }) {
    const data = iniciarData(dados); 
    return (
        <Container>
            <Titulo>{titulo}</Titulo>
            <Grafico>
              <LineTemplate 
              data={data}
              options={options}
              />
            </Grafico>
        </Container>
    );
}

export default Line;