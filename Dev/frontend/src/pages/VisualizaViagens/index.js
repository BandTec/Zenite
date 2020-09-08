import React, { useEffect, useState } from "react";
import api from "../../services/api";
//Então, é que agora tá basicamente "pronta"

import { Container, Row } from "./styles";
import Tabela from "../../components/Tabela2";
import Paginacao from "../../components/Paginacao";

import Loader from "./../../components/Loader";
import CabecalhoConsulta from "../../components/CabecalhoConsulta";
import {reformatarData} from "../../functions/Mascaras/mask";

export default function ConsultaViagem() {
  const [corpo, setCorpo] = useState([]);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
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
          rota = ""
          break;
      }

      //Essa linha de baixo pega o token de autenticação do localStorage
      const token = localStorage.getItem("token");

      //Essa de baixo, faz a chamada GET pra rota /api/linha, passando o token como cabeçalho e passa pra
      //uma variavel response
      const response = await api.get(`/api/viagem${rota}?pagina=${pagina}`, {
        headers: { Authorization: token },
      });

      //aqui pego do response.data que é onde tá os dados da linha e passo pra uma variavel tbm
      let dados = response.data;
      setTotal(dados.totalPaginas);
      setTotalItens(dados.totalItens);

      let temp = [];

      dados.lista.forEach((item) => {
        const saida = item.horaSaida.substring(11, 19);
        const chegada = item.horaChegada.substring(11, 19);

        temp.push(criaDados(
          item.carro.placa, 
          item.linha.numero,
          item.motorista.nome,
          item.fiscal.nome,
          item.fiscalVolta.nome,
          saida, 
          chegada,
          item.qtdPassageiros
          )
          );
      });
      setCorpo(temp);
    }

    dadosCorpos();
  }, [pagina]);

  function criaDados(onibus, linha, motorista, fiscal, fiscal_volta, horaSaida, horaChegada, qtdPassageiros) {
    return { onibus, linha, motorista, fiscal, fiscal_volta, horaSaida, horaChegada, qtdPassageiros };
  }

  return corpo.length <= 0 ? (
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
