import React, { useEffect, useState } from 'react';
import api from '../../services/api';
//Então, é que agora tá basicamente "pronta"


import { Container, Row, Acoes } from './styles';
import Botao from '../../components/Botao';
import Tabela from "./../../components/Tabela2";
import Titulo from '../../components/Titulo';
import Paginacao from '../../components/Paginacao';

export default function ConsultaOnibus() {
  const [corpo, setCorpo] = useState([]);
  
  useEffect(()=> {
    async function dadosCorpos() {
      //Essa linha de baixo pega o token de autenticação do localStorage
      const token = localStorage.getItem('token');
      
      //Essa de baixo, faz a chamada GET pra rota /api/onibus, passando o token como cabeçalho e passa pra 
      //uma variavel response
      const response = await api.get('/api/onibus',{
        headers: {'Authorization': token}
      })
      
      //aqui pego do response.data que é onde tá os dados do onibus e passo pra uma variavel tbm
      let dados = response.data;

      let temp = [];

      dados.forEach( item => {
        temp.push(
          criaDados(
            item.id,
            item.numero,
            item.dispositivo.codigo,
    
          )
        );
      });
      setCorpo(temp);
    }

    dadosCorpos();
  }, []);
  
  function criaDados(id,numero, dispositivo ){
    return {id, numero, dispositivo}
  }

  return (
    <Container>
        <Row>
            <Titulo textoMenor="consulta de ônibus" textoMaior="" />
        </Row>

        <Acoes>
          <Botao
              descricao="Novo Ônibus"
              estiloEscuro={true}
              url="/onibus/cadastro"
            />
        
          <Botao descricao="relatório" url="/onibus" />
        </Acoes>
    

        <Row>
          <Tabela
            tipo="onibus"
            dados={corpo}
            detalhes={false}
          />
        </Row>

        <Row>
          <Paginacao />
        </Row>
    </Container>
  );
}
