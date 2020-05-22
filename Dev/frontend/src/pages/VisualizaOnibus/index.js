import React, { useEffect, useState } from "react";
import api from "../../services/api";
//Então, é que agora tá basicamente "pronta"

import { Container, Row, Acoes } from "./styles";
import Botao from "../../components/Botao";
import Tabela from "./../../components/Tabela2";
import Titulo from "../../components/Titulo";
import Paginacao from "../../components/Paginacao";

export default function ConsultaOnibus() {
  const [corpo, setCorpo] = useState([]);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
  const [totalItens, setTotalItens] = useState(0);
  const [atual, setAtual] = useState(0);

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

      setAtual(dados.paginaAtual);
      setTotal(dados.totalPaginas);
      setTotalItens(dados.totalItens);

      let temp = [];

      dados.lista.forEach((item) => {
        let acessivel = item.acessivel ? "sim" : "não";
        temp.push(criaDados(
          item.id, 
          item.numero, 
          item.placa,
          item.modelo,
          item.fabricante,
          acessivel,
          item.dispositivo.codigo,
          item.gerente ? item.gerente.nome : "Sem gerente"
          ));
      });
      setCorpo(temp);
    }

    dadosCorpos();
  }, [pagina]);

  function criaDados(id, numero, placa, modelo, fabricante, acessivel, dispositivo, gerente) {
    return { id, numero, placa, modelo, fabricante, acessivel, dispositivo, gerente };
  }

  return (
    <Container>
      <Row>
        <Titulo textoMenor="consulta de ônibus" textoMaior="" />
      </Row>

      <Acoes>
        <Botao
          descricao="Novo Ônibus"
          estiloEscuro={true}
          url="/onibus/cadastro"
        />

        <Botao descricao="relatório" url="/onibus" />
      </Acoes>

      <Row>
        <Tabela tipo="onibus" dados={corpo} detalhes={false} />
      </Row>

      <Row>
        <Paginacao
          pgAtual={atual}
          totalPg={total}
          voltar={() => setPagina(pagina - 1)}
          proximo={() => setPagina(pagina + 1)}
          totalItens={totalItens}
        />
      </Row>
    </Container>
  );
}
