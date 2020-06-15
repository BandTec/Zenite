import React from 'react';
import { Radar as RadarTemplate } from 'react-chartjs-2';

import { iniciarData, options } from './config';
import { Container, Titulo, Grafico } from './styles';

function Radar({ titulo = "Gráfico", dados = { labels: [], data: []} }) {
    
    const data = iniciarData(dados);

    return (
    <Container>
        <Titulo>{titulo}</Titulo>
        <Grafico>
            <RadarTemplate 
                data={data}
                options={options}
            />
        </Grafico>
    </Container>
  )
}

export default Radar;