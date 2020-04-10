import React, { useEffect, useState } from 'react';
import api from '../../services/api';
//Então, é que agora tá basicamente "pronta"

import { Container, Row, BotaoRelatorio ,BotaoNovaLinha, Tela, Acoes, Cabecalho, CaixaTabela } from './styles';
import Botao from '../../components/Botao';
import Tabela from './Tabela';
import Titulo from '../../components/Titulo';
import Paginacao from '../../components/Paginacao';

export default function CadastroLinha() {
  let dados = [];
  const [corpo, setCorpo] = useState([]);
  
  async function dadosCorpos() {
    //Essa linha de baixo pega o token de autenticação do localStorage
    const token = localStorage.getItem('token');
    
    //Essa de baixo, faz a chamada GET pra rota /api/linha, passando o token como cabeçalho e passa pra 
    //uma variavel response
    const response = await api.get('/api/linha',{
      headers: {'Authorization': token}
    })
    
    console.log(response.data);
    //aqui pego do response.data que é onde tá os dados da linha e passo pra uma variavel tbm
    dados = response.data;

    let temp = [];

    dados.forEach( item => {
      temp.push(
        criaDados(
          item.numero,
          item.pontoIda.nome,
          item.pontoVolta.nome,
        )
      );
    });
    console.log(temp)
    setCorpo(temp);
  }

  useEffect(dadosCorpos, []);
  
  function criaDados(numero, pontoIda, pontoVolta, acoes){
    return {numero, pontoIda, pontoVolta, acoes}
  }

  const dadosCabecalho = [
    criaDados('Numero', 'Ponto Ida', 'Ponto Volta', 'Ações')
  ];

  return (
    <Container>
      <Tela>
        <Row>
          <Cabecalho>
            <Titulo textoMenor="consulta de linha" textoMaior="" />
          </Cabecalho>
        </Row>
        <Row>
          <Acoes>
            <BotaoNovaLinha>
              <Botao
                descricao="Nova Linha"
                estiloEscuro={true}
                url="/linha/cadastro/1"
              />
            </BotaoNovaLinha>

            <BotaoRelatorio>
              <Botao descricao="relatório" url="/linha" />
            </BotaoRelatorio>
          </Acoes>
        </Row>

        <Row>
          <CaixaTabela>
            <Tabela
              tabela={2}
              dadosCabecalho={dadosCabecalho}
              dadosCorpo={corpo}
            />
          </CaixaTabela>
        </Row>

        <Row>
          <Paginacao />
        </Row>
      </Tela>
    </Container>
  );
}
