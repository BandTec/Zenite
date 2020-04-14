import React, { useEffect, useState } from 'react';
import api from '../../services/api';

import { Container, Row, BotaoRelatorio ,BotaoNovoMotorista, Tela, Acoes, Cabecalho, CaixaTabela } from './styles';
import Botao from '../../components/Botao';
import Tabela from './Tabela';
import Titulo from '../../components/Titulo';
import Paginacao from '../../components/Paginacao';

export default function CadasrtroMotorista() {
    let dados = [];
  const [corpo, setCorpo] = useState([]);

  async function dadosCorpos() {
    //Essa linha de baixo pega o token de autenticação do localStorage
    const token = localStorage.getItem('token');
    
    //Essa de baixo, faz a chamada GET pra rota /api/linha, passando o token como cabeçalho e passa pra 
    //uma variavel response
    const response = await api.get('/api/motorista',{
      headers: {'Authorization': token}
    })

    console.log(response.data);
    //aqui pego do response.data que é onde tá os dados da linha e passo pra uma variavel tbm
    dados = response.data;

    let temp = [];

    
    dados.forEach( item => {
        temp.push(
          criaDados(
            item.nome,
            item.numeroTelefone,
            item.cpf,
            item.cnh
          )
        );
      });
      console.log(temp)
      setCorpo(temp);
   }
 
   useEffect(dadosCorpos, []);

   function criaDados(nome,numeroTelefone, cpf, cnh, acoes){
    return {nome,numeroTelefone, cpf, cnh, acoes}
  }

  const dadosCabecalho = [
    criaDados('Nome', 'Telefone','CPF', 'CNH','Ações')
  ];

  return (
    <Container>
    <Tela>
      <Row>
        <Cabecalho>
          <Titulo textoMenor="consulta de motorista" textoMaior="" />
        </Cabecalho>
      </Row>
      <Row>
        <Acoes>
          <BotaoNovoMotorista>
            <Botao
              descricao="Novo Motorista"
              estiloEscuro={true}
              url="/motorista/cadastro/1"
            />
          </BotaoNovoMotorista>

          <BotaoRelatorio>
            <Botao descricao="relatório" url="/motorista" />
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
