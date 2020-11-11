import React, { useEffect, useState } from "react";
import consultar from "../../services/metodos/consultar";

import { Container, Row, Detalhes } from "./styles";
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
  const [dataCronograma, setDataCronograma] = useState("");
  const [fiscal, setFiscal] = useState(0);

  useEffect(() => {
    async function consultarMotorista() {
      const url = `/api/horarios/cronograma/${id}?pagina=${pagina}`;
      const resultado = await consultar(url, criaDados);
      setCorpo(resultado.dados);
      setTotal(resultado.totalPaginas);
      setTotalItens(resultado.totalItens);
      setLoading(false);
      console.log(resultado);
      setDataCronograma(
        resultado.rawData[0].horaPrevistaChegada.replace(
          /(\d{4})-(\d{2})-(\d{2})(.*)/g,
          "$3/$2/$1"
        )
      );
      setFiscal(resultado.rawData[0].linha.fiscal);
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
      carro,
      motorista,
      horaPrevistaSaida,
      horaPrevistaChegada,
      viagemStatus,
    } = item;

    return {
      id_motorista: motorista.id,
      carro: carro.placa,
      motorista: motorista.nome,
      saida: horaPrevistaSaida.replace(/.*T(..:..)(.*)/g, "$1"),
      chegada: horaPrevistaChegada.replace(/.*T(..:..)(.*)/g, "$1"),
      status_da_viagem: getStatus(viagemStatus),
    };
  }

  return corpo.length <= 0 && loading ? (
    <Loader />
  ) : (
    <Container>
      <CabecalhoConsulta titulo="Horarios" totalItens={totalItens} />

      <Detalhes>
        <div>
          <h5>Data</h5>
          <p>{dataCronograma}</p>
        </div>

        <div>
          <h5>Fiscal</h5>
          <p>{fiscal}</p>
        </div>
      </Detalhes>

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
