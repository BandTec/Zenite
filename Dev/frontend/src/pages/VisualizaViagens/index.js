import React, { useEffect, useState } from "react";
import consultar from "../../services/metodos/consultar";

import { Container, Row } from "./styles";
import Tabela from "../../components/Tabela2";
import Paginacao from "../../components/Paginacao";

import Loader from "./../../components/Loader";
import CabecalhoConsulta from "../../components/CabecalhoConsulta";

export default function ConsultaViagem() {
  const [corpo, setCorpo] = useState([]);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
  const [loading, setLoading] = useState(true);
  const [totalItens, setTotalItens] = useState(0);

  useEffect(() => {
    async function dadosCorpos() {
      const nivel = localStorage.getItem("nivel");
      const id = localStorage.getItem("id");
      let rota;
      switch (nivel) {
        case 4:
          rota = "/fiscal/" + id;
          break;
        case 5:
          rota = "/motorista/" + id;
          break;
        default:
          rota = "";
          break;
      }

      const url = `/api/viagem${rota}?pagina=${pagina}`;
      const resultado = await consultar(url, criaDados);
      setCorpo(resultado.dados);
      setTotal(resultado.totalPaginas);
      setTotalItens(resultado.totalItens);
      setLoading(false);
    }

    dadosCorpos();
  }, [pagina]);

  function criaDados(item) {
    const {
      onibus,
      linha,
      motorista,
      fiscal,
      fiscal_volta,
      horaSaida,
      horaChegada,
      qtdPassageiros,
    } = item;
    const saida = horaSaida.substring(11, 19);
    const chegada = horaChegada.substring(11, 19);
    return {
      onibus,
      linha,
      motorista,
      fiscal,
      fiscal_volta,
      horaSaida: saida,
      horaChegada: chegada,
      qtdPassageiros,
    };
  }

  return corpo.length <= 0 && loading ? (
    <Loader />
  ) : (
    <Container>
      <CabecalhoConsulta titulo="Viagens" totalItens={totalItens} />

      <Row>
        <Tabela tipo="viagem" dados={corpo} detalhes={false} />
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
