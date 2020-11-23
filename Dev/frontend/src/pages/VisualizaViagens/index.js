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
      console.log(resultado.dados);
      setCorpo(resultado.dados.reverse());
      setTotal(resultado.totalPaginas);
      setTotalItens(resultado.totalItens);
      setLoading(false);
    }

    dadosCorpos();
  }, [pagina]);

  function criaDados(item) {
    const {
      carro,
      linha,
      motorista,
      fiscal,
      fiscalVolta,
      horaSaida,
      horaChegada,
      qtdPassageiros,
    } = item;
    const saida = horaSaida.substring(11, 19);
    const chegada = horaChegada.substring(11, 19);
    const data = new Date(horaChegada);
    return {
      data: data.toLocaleDateString("br-PT"),
      linha: linha.numero,
      motorista: motorista.nome,
      fiscal_ida: fiscal.nome,
      fiscal_volta: fiscalVolta.nome,
      hora_saida: saida,
      hora_chegada: chegada,
      qtd_de_passageiros: qtdPassageiros,
    };
  }

  return corpo.length <= 0 && loading ? (
    <Loader />
  ) : (
    <Container>
      <CabecalhoConsulta titulo="Viagens" totalItens={totalItens} />

      <Row>
        <Tabela tipo="viagem" dados={corpo} temAcoes={false} />
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
