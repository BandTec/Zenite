import React, { useEffect, useState } from 'react';
import api from '../../services/api';

import { Container, Row, Acoes } from './styles';
import Botao from '../../components/Botao';
import Tabela from '../../components/Tabela2'
import Titulo from '../../components/Titulo';
import Paginacao from '../../components/Paginacao';

export default function VisualizaGerente() {
  const [corpo, setCorpo] = useState([]);
  
  useEffect(() => {
    async function dadosCorpos() {
      const token = localStorage.getItem("token");

      const response = await api.get("/api/gerente", {
        headers: { Authorization: token },
      });
      let dados = response.data;

      let temp = [];
      dados.forEach((item) => {
          temp.push(
            criaDados(
              item.id,
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
  
  function criaDados(id, nome, telefone, cpf){
    return {id, nome, telefone, cpf}
  }

  return (
    <Container>
      <Row>
        <Titulo textoMenor="consulta do gerente" textoMaior="" />
      </Row>

      <Acoes>
        <Botao
          descricao="Novo Gerente"
          estiloEscuro={true}
          url="/gerente/cadastro"
        />

        <Botao descricao="relatório" url="/gerente" />
      </Acoes>

      <Row>
        <Tabela dados={corpo} tipo="gerente" />
      </Row>

      <Row>
        <Paginacao />
      </Row>
    </Container>
  );
}
