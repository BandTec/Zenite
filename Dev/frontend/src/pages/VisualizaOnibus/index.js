import React, { useEffect, useState } from "react";
import consultar from "../../services/metodos/consultar";

import { Container, Row } from "./styles";
import Tabela from "./../../components/Tabela2";
import Paginacao from "../../components/Paginacao";
import Loader from "./../../components/Loader";
import CabecalhoConsulta from "../../components/CabecalhoConsulta";

export default function ConsultaOnibus() {
  const [corpo, setCorpo] = useState([]);
  const [loading, setLoading] = useState(true);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
  const [totalItens, setTotalItens] = useState(0);

  useEffect(() => {
    async function consultarOnibus() {
      const url = `/api/onibus?pagina=${pagina}`;
      const resultado = await consultar(url, criaDados);
      setCorpo(resultado.dados);
      setTotal(resultado.totalPaginas);
      setTotalItens(resultado.totalItens);
      setLoading(false);
    }

    consultarOnibus();
  }, [pagina]);

  function criaDados(item) {
    let acessivel = item.acessivel ? "Sim" : "Não";
    const { id, numero, placa, modelo, motorista, linha } = item;
    return {
      id,
      numero,
      placa,
      modelo,
      acessivel: acessivel,
      motorista: motorista || "Sem motorista",
      linha: linha || "Sem linha",
    };
  }

  return corpo.length <= 0 && loading ? (
    <Loader />
  ) : (
    <Container>
      <CabecalhoConsulta
        botaoTitulo="Novo Ônibus"
        titulo="Ônibus"
        url="onibus"
        totalItens={totalItens}
      />

      <Row>
        <Tabela tipo="onibus" dados={corpo} />
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
