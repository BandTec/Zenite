import React, { useEffect, useState } from 'react';
import api from '../../services/api';

import { Container, Row, Acoes } from './styles';
import Botao from '../../components/Botao';
import Tabela from '../../components/Tabela2'
import Titulo from '../../components/Titulo';
import Paginacao from '../../components/Paginacao';

export default function VisualizaFiscal() {
  const [corpo, setCorpo] = useState([]);
  
  useEffect(() => {
    async function dadosCorpos() {
      const token = localStorage.getItem("token");

      const response = await api.get("/api/fiscal", {
        headers: { Authorization: token },
      });
      let dados = response.data;

      let temp = [];
      dados.forEach((item) => {
          temp.push(
            criaDados(
              item.id,
              item.registroFiscal,
              item.nome,
              item.numeroTelefone,
              item.cpf
            )
          );
      });
      setCorpo(temp);
  }
    dadosCorpos();
  }, []);
  
  function criaDados(id, registro, nome, telefone, cpf){
    return {id, registro, nome, telefone, cpf}
  }

  return (
    <Container>
      <Row>
        <Titulo textoMenor="consulta do fiscal" textoMaior="asdfdsa" />
      </Row>

      <Acoes>
          <Botao
            descricao="Novo Fiscal"
            estiloEscuro={true}
            url="/fiscal/cadastro/0"
          />

          <Botao descricao="relatório" url="/fiscal" />
      </Acoes>

      <Row>
          <Tabela dados={corpo} tipo="fiscal" />
      </Row>

      <Row>
        <Paginacao />
      </Row>
    </Container>
  );
}
