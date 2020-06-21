import React from 'react';

import { Container, Titulo, Busca, Tabela } from './styles';

import { useState, useEffect } from 'react';

function Table({titulo = "Tabela", placeholder="Pesquise...", dados = {header: [], body: []}}) {
    const [dadosTabela, setDadosTabela] = useState({header: [], body: []});
    useEffect(() => {
        setDadosTabela(dados)
    },[dados])

    const search = event => {
        let aux = []
        if(event.target.value){
            for(const body of dados.body)
                for(const dado of body)
                    if(typeof dado === 'string' && dado.indexOf(event.target.value) !== -1)
                        aux.push(body)
            setDadosTabela({header: dadosTabela.header, body: aux})
            aux = []
        }else{
            setDadosTabela(dados)
        }
    }

    return (
      <Container>
          <Titulo>{titulo}</Titulo>
          <Busca placeholder={placeholder} onChange={e => search(e)}/>
          { dadosTabela ?
            <Tabela>
                <thead>
                    <tr>
                        {dadosTabela.header.map(colunaCabecalho => 
                               <th>{colunaCabecalho}</th>
                        )}
                    </tr>
                </thead>
                <tbody>

                    {dadosTabela.body.map(linha => 
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