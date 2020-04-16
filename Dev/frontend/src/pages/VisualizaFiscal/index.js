import React, { useEffect, useState } from 'react';
import api from '../../services/api';

import { Container, Row, BotaoRelatorio ,BotaoNovoFiscal, Tela, Acoes, Cabecalho, CaixaTabela } from './styles';
import Botao from '../../components/Botao';
import Tabela from '../../components/Tabela2'
import Titulo from '../../components/Titulo';
import Paginacao from '../../components/Paginacao';

export default function CadastroFiscal() {
  let dados = [];
  const [corpo, setCorpo] = useState([]);
  
  async function dadosCorpos() {
    const token = localStorage.getItem('token');
    
    const response = await api.get('/api/fiscal',{
      headers: {'Authorization': token}
    })
    dados = response.data;

     let temp = [];

     dados.forEach( item => {
       temp.push(
         criaDados(
           item.registroFiscal,
           item.nome,
           item.numeroTelefone,
           item.cpf
         )
       );
     });
     setCorpo(temp);
  }

  useEffect(dadosCorpos, []);
  
  function criaDados(registro, nome, telefone, cpf){
    return {registro, nome, telefone, cpf}
  }

  return (
    <Container>
      <Tela>
        <Row>
          <Cabecalho>
            <Titulo textoMenor="consulta do fiscal" textoMaior="" />
          </Cabecalho>
        </Row>
        <Row>
          <Acoes>
            <BotaoNovoFiscal>
              <Botao
                descricao="Novo Fiscal"
                estiloEscuro={true}
                url="/fiscal/cadastro/0"
              />
            </BotaoNovoFiscal>

            <BotaoRelatorio>
              <Botao descricao="relatório" url="/fiscal" />
            </BotaoRelatorio>
          </Acoes>
        </Row>

        <Row>
          <CaixaTabela>
            <Tabela dados={corpo} />
          </CaixaTabela>
        </Row>

        <Row>
          <Paginacao />
        </Row>
      </Tela>
    </Container>
  );
}
