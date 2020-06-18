import React, { useEffect, useState } from "react";
import api from "../../services/api";

import { Container, Row } from "./styles";
import Tabela from "../../components/Tabela2";
import Paginacao from "../../components/Paginacao";
import Loader from "./../../components/Loader";
import CabecalhoConsulta from "../../components/CabecalhoConsulta";

export default function VisualizaFiscal() {
  const [corpo, setCorpo] = useState([]);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
    const [totalItens, setTotalItens] = useState(0);

  useEffect(() => {
    async function dadosCorpos() {
      const token = localStorage.getItem("token");

      const response = await api.get(`/api/fiscal?pagina=${pagina}`, {
        headers: { Authorization: token },
      });
      let dados = response.data;
      setTotal(dados.totalPaginas);
      setTotalItens(dados.totalItens);

      let temp = [];
      dados.lista.forEach((item) => {
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
  }, [pagina]);

  function criaDados(id, registro, nome, telefone, cpf) {
    return { id, registro, nome, telefone, cpf };
  }

  return corpo.length <= 0 ? (
    <Loader />
  ) : (
    <Container>
      <CabecalhoConsulta
        botaoTitulo="Novo Fiscal"
        titulo="Fiscal"
        url="fiscal"
        totalItens={totalItens}
      />

      <Row>
        <Tabela dados={corpo} tipo="fiscal" />
      </Row>

      <Row>
        <Paginacao
          pgAtual={pagina}
          totalPg={total}
          mudarPag={(p) => setPagina(p)}
          totalItens={totalItens}
        />
      </Row>
    </Container>
  );
}
