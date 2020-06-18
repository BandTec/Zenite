import React, { useEffect, useState } from "react";
import api from "../../services/api";
//Então, é que agora tá basicamente "pronta"

import { Container, Row } from "./styles";
import Tabela from "./../../components/Tabela2";
import Paginacao from "../../components/Paginacao";
import Loader from "./../../components/Loader";
import CabecalhoConsulta from "../../components/CabecalhoConsulta";

export default function ConsultaLinha() {
  const [corpo, setCorpo] = useState([]);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
  const [totalItens, setTotalItens] = useState(0);

  useEffect(() => {
    async function dadosCorpos() {
      //Essa linha de baixo pega o token de autenticação do localStorage
      const token = localStorage.getItem("token");

      //Essa de baixo, faz a chamada GET pra rota /api/linha, passando o token como cabeçalho e passa pra
      //uma variavel response
      const response = await api.get(`/api/linha?pagina=${pagina}`, {
        headers: { Authorization: token },
      });

      //aqui pego do response.data que é onde tá os dados da linha e passo pra uma variavel tbm
      let dados = response.data;
      setTotal(dados.totalPaginas);
      setTotalItens(dados.totalItens);

      let temp = [];

      dados.lista.forEach((item) => {
        temp.push(
          criaDados(
            item.id,
            item.numero,
            item.pontoIda.nome,
            item.pontoVolta.nome,
            item.carrosId.length
          )
        );
      });
      setCorpo(temp);
    }

    dadosCorpos();
  }, [pagina]);

  function criaDados(
    id,
    numero,
    parada_inicial,
    parada_final,
    quantidade_de_onibus
  ) {
    return { id, numero, parada_inicial, parada_final, quantidade_de_onibus };
  }

  return corpo.length <= 0 ? (
    <Loader />
  ) : (
    <Container>
      <CabecalhoConsulta
        botaoTitulo="Nova Linha"
        titulo="Linha"
        url="linha"
        totalItens={totalItens}
      />

      <Row>
        <Tabela tipo="linha" dados={corpo} />
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
