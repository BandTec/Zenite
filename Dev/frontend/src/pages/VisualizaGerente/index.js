import React, { useEffect, useState } from "react";
import api from "../../services/api";

import { Container, Row, Acoes } from "./styles";
import Botao from "../../components/Botao";
import Tabela from "../../components/Tabela2";
import Titulo from "../../components/Titulo";
import Paginacao from "../../components/Paginacao";

export default function VisualizaGerente() {
  const [corpo, setCorpo] = useState([]);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
  const [atual, setAtual] = useState(0);
  const [totalItens, setTotalItens] = useState(0);

  useEffect(() => {
    async function dadosCorpos() {
      const token = localStorage.getItem("token");

      const response = await api.get(`/api/gerente?pagina=${pagina}`, {
        headers: { Authorization: token },
      });
      let dados = response.data;
      setAtual(dados.paginaAtual);
      setTotal(dados.totalPaginas);
      setTotalItens(dados.totalItens);
      let temp = [];
      dados.lista.forEach((item) => {
        temp.push(criaDados(item.id, item.nome, item.numeroTelefone, item.cpf));
      });
      setCorpo(temp);
    }
    dadosCorpos();
  }, [pagina]);

  function criaDados(id, nome, telefone, cpf) {
    return { id, nome, telefone, cpf };
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
        <Paginacao
          pgAtual={atual}
          totalPg={total}
          voltar={() => setPagina(pagina - 1)}
          proximo={() => setPagina(pagina + 1)}
          totalItens={totalItens}
        />
      </Row>
    </Container>
  );
}
