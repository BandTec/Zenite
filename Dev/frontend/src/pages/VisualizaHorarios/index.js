import React, { useEffect, useState } from "react";
import consultar from "../../services/metodos/consultar";

import { Container, Row } from "./styles";
import Tabela from "../../components/Tabela2";
import Paginacao from "../../components/Paginacao";
import Loader from "../../components/Loader";
import CabecalhoConsulta from "../../components/CabecalhoConsulta";

export default function ConsultaHorarios(props) {
  const id = props.match.params.id;
  const [loading, setLoading] = useState(true);
  const [corpo, setCorpo] = useState([]);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
  const [totalItens, setTotalItens] = useState(0);

  useEffect(() => {
    async function consultarMotorista() {
      const url = `/api/horarios/cronograma/${id}?pagina=${pagina}`;
      const resultado = await consultar(url, criaDados);
      setCorpo(resultado.dados);
      setTotal(resultado.totalPaginas);
      setTotalItens(resultado.totalItens);
      setLoading(false);
    }

    consultarMotorista();
  }, [pagina]);

  function getStatus(status) {
    switch (status) {
      case 1:
        return "aguardando";
      case 2:
        return "em viagem";
      case 3:
        return "finalizada";
      default:
        return "indefinido";
    }
  }

  function criaDados(item) {
    const {
      idCronogramaHorarios,
      carro,
      linha,
      motorista,
      horaPrevistaSaida,
      horaPrevistaChegada,
      viagemStatus,
    } = item;
    return {
      id_motorista: motorista.id,
      carro: carro.placa,
      motorista: motorista.nome,
      saida: horaPrevistaSaida,
      chegada: horaPrevistaChegada,
      status_da_viagem: getStatus(viagemStatus),
    };
  }

  return corpo.length <= 0 && loading ? (
    <Loader />
  ) : (
    <Container>
      <CabecalhoConsulta titulo="Horarios" totalItens={totalItens} />

      <Row>
        <Tabela tipo="motorista" dados={corpo} temAcoes={false} />
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
