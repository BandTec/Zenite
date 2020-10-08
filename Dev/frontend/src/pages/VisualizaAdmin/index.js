/* eslint react-hooks/exhaustive-deps: 0 */
import React, { useEffect, useState } from "react";
import consultar from "../../services/metodos/consultar";

import { Container, Row } from "./styles";
import Tabela from "../../components/Tabela2";
import Paginacao from "../../components/Paginacao";
import Loader from "./../../components/Loader";
import CabecalhoConsulta from "../../components/CabecalhoConsulta";

export default function ConsultaAdmin() {
  const [corpo, setCorpo] = useState([]);
  const [loading, setLoading] = useState(true);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
  const [totalItens, setTotalItens] = useState(0);

  useEffect(() => {
    async function consultarAdmins() {
      const url = `/api/administrador?pagina=${pagina}`;
      const resultado = await consultar(url, criaDados);
      setCorpo(resultado.dados);
      setTotal(resultado.totalPaginas);
      setTotalItens(resultado.totalItens);
      setLoading(false);
    }

    consultarAdmins();
  }, []);

  function criaDados(item) {
    const { id, nome, conta } = item;
    return { id, nome, email: conta.email };
  }

  return corpo.length <= 0 && loading ? (
    <Loader />
  ) : (
    <Container>
      <CabecalhoConsulta
        botaoTitulo="Novo Administrador"
        titulo="administradores"
        url="administrador"
        totalItens={totalItens}
      />

      <Row>
        <Tabela tipo="administrador" dados={corpo} detalhes={false} />
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
