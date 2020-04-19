import React, { useEffect, useState } from 'react';
import api from '../../services/api';
//Então, é que agora tá basicamente "pronta"

import { Container, Row, Acoes } from './styles';
import Botao from '../../components/Botao';
import Tabela from "./../../components/Tabela2";
import Titulo from '../../components/Titulo';
import Paginacao from '../../components/Paginacao';

export default function ConsultaLinha() {
  const [corpo, setCorpo] = useState([]);
  
  useEffect(()=> {
    async function dadosCorpos() {
      //Essa linha de baixo pega o token de autenticação do localStorage
      const token = localStorage.getItem('token');
      
      //Essa de baixo, faz a chamada GET pra rota /api/linha, passando o token como cabeçalho e passa pra 
      //uma variavel response
      const response = await api.get('/api/linha',{
        headers: {'Authorization': token}
      })
      
      //aqui pego do response.data que é onde tá os dados da linha e passo pra uma variavel tbm
      let dados = response.data;

      let temp = [];

      dados.forEach( item => {
        temp.push(
          criaDados(
            item.id,
            item.numero,
            item.pontoIda.nome,
            item.pontoVolta.nome,
          )
        );
      });
      setCorpo(temp);
    }

    dadosCorpos();
  }, []);
  
  function criaDados(id,numero, pontoIda, pontoVolta){
    return {id, numero, pontoIda, pontoVolta}
  }

  return (
    <Container>
        <Row>
            <Titulo textoMenor="consulta de linha" textoMaior="" />
        </Row>

        <Acoes>
          <Botao
              descricao="Nova Linha"
              estiloEscuro={true}
              url="/linha/cadastro/1"
            />
        
          <Botao descricao="relatório" url="/linha" />
        </Acoes>
    

        <Row>
          <Tabela
            tipo="linha"
            dados={corpo}
          />
        </Row>

        <Row>
          <Paginacao />
        </Row>
    </Container>
  );
}
