import React, { useEffect, useState } from "react";
import api from "../../services/api";
//Então, é que agora tá basicamente "pronta"

import { Container, Row } from "./styles";

import Tabela from "./../../components/Tabela2";
import Paginacao from "../../components/Paginacao";
import Loader from "./../../components/Loader";
import CabecalhoConsulta from "../../components/CabecalhoConsulta";

export default function ConsultaOnibus() {
  const [corpo, setCorpo] = useState([]);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
  const [totalItens, setTotalItens] = useState(0);

  useEffect(() => {
    async function dadosCorpos() {
      //Essa linha de baixo pega o token de autenticação do localStorage
      const token = localStorage.getItem("token");

      //Essa de baixo, faz a chamada GET pra rota /api/onibus, passando o token como cabeçalho e passa pra
      //uma variavel response
      const response = await api.get(`/api/onibus?pagina=${pagina}`, {
        headers: { Authorization: token },
      });

      //aqui pego do response.data que é onde tá os dados do onibus e passo pra uma variavel tbm
      let dados = response.data;
      setTotal(dados.totalPaginas);
      setTotalItens(dados.totalItens);

      let temp = [];

      dados.lista.forEach((item) => {
        let acessivel = item.acessivel ? "Sim" : "Não";
        temp.push(
          criaDados(
            item.id,
            item.numero,
            item.placa,
            item.modelo,
            // item.fabricante,
            acessivel,
            // item.dispositivo.codigo,
            // item.gerente ? item.gerente.nome : "Sem gerente",
            item.motorista || "Sem motorista",
            item.linha || "Sem linha"
          )
        );
      });
      setCorpo(temp);
    }

    dadosCorpos();
  }, [pagina]);

  function criaDados(id, numero, placa, modelo, acessivel, motorista, linha) {
    return { id, numero, placa, modelo, acessivel, motorista, linha };
  }

  return corpo.length <= 0 ? (
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
