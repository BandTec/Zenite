import React, { useEffect, useState } from "react";
import consultar from "../../services/metodos/consultar";

import { Container, Row } from "./styles";
import Tabela from "./../../components/Tabela2";
import Paginacao from "../../components/Paginacao";
import Loader from "./../../components/Loader";
import CabecalhoConsulta from "../../components/CabecalhoConsulta";

export default function ConsultaMotorista() {
  const [loading, setLoading] = useState(true);
  const [corpo, setCorpo] = useState([]);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
  const [totalItens, setTotalItens] = useState(0);

  useEffect(() => {
    async function consultarMotorista() {
      const url = `/api/motorista?pagina=${pagina}`;
      const resultado = await consultar(url, criaDados);
      setCorpo(resultado.dados);
      setTotal(resultado.totalPaginas);
      setTotalItens(resultado.totalItens);
      setLoading(false);
    }

    consultarMotorista();
  }, [pagina]);

  function criaDados(item) {
    const { id, nome, numeroTelefone, cpf, cnh, carro } = item;
    return {
      id,
      nome,
      telefone: numeroTelefone,
      cpf,
      cnh,
      alocado_a_onibus: carro ? carro.numero : "Sem ônibus",
    };
  }

  return corpo.length <= 0 && loading ? (
    <Loader />
  ) : (
    <Container>
      <CabecalhoConsulta
        botaoTitulo="Novo Motorista"
        titulo="Motorista"
        url="motorista"
        totalItens={totalItens}
      />

      <Row>
        <Tabela tipo="motorista" dados={corpo} />
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
