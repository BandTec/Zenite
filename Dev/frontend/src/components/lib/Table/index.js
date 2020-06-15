import React from 'react';

import { Container, Titulo, Busca, Tabela } from './styles';

function Table({titulo = "Tabela", placeholder="Pesquise...", dados}) {
    
    return (
      <Container>
          <Titulo>{titulo}</Titulo>
          <Busca placeholder={placeholder}/>
          { dados ?
            <Tabela>
                <thead>
                    <tr>
                        {dados.header.map(colunaCabecalho => 
                               <th>{colunaCabecalho}</th>
                        )}
                    </tr>
                </thead>
                <tbody>
                    {dados.body.map(linha => 
                        <tr>{
                            linha.map(coluna => 
                                <td>{coluna}</td>
                            )
                        }</tr>
                    )}
                </tbody>
            </Tabela>
            : <h1>Vazio</h1>
          }
      </Container>
  );
}

export default Table;