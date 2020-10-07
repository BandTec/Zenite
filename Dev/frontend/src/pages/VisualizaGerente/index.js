import React, { useEffect, useState } from "react";

import { Container, Row } from "./styles";
import Tabela from "../../components/Tabela2";
import Paginacao from "../../components/Paginacao";
import Loader from "./../../components/Loader";
import CabecalhoConsulta from "../../components/CabecalhoConsulta";
import consultar from "../../services/metodos/consultar";

export default function VisualizaGerente() {
  const [corpo, setCorpo] = useState([]);
  const [loading, setLoading] = useState(true);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
  const [totalItens, setTotalItens] = useState(0);

  useEffect(() => {
    async function consultarGerentes() {
      const url = `/api/gerente?pagina=${pagina}`;
      const resultado = await consultar(url, criaDados);
      setCorpo(resultado.dados);
      setTotal(resultado.totalPaginas);
      setTotalItens(resultado.totalItens);
      setLoading(false);
    }

    consultarGerentes();
  }, [pagina]);

  function criaDados(item) {
    const { id, nome, numeroTelefone, cpf } = item;
    return { id, nome, telefone: numeroTelefone, cpf };
  }

  return corpo.length <= 0 && loading ? (
    <Loader />
  ) : (
    <Container>
      <CabecalhoConsulta
        botaoTitulo="Novo gerente"
        titulo="Gerente"
        url="gerente"
        totalItens={totalItens}
      />

      <Row>
        <Tabela dados={corpo} tipo="gerente" />
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
