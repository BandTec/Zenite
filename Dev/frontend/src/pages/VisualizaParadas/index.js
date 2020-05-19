import React, { useEffect, useState } from "react";
import api from "../../services/api";
//Então, é que agora tá basicamente "pronta"

import { Container, Row, Acoes } from "./styles";
import Botao from "../../components/Botao";
import Tabela from "../../components/Tabela2";
import Titulo from "../../components/Titulo";
import Paginacao from "../../components/Paginacao";

export default function ConsultaParada() {
  const [corpo, setCorpo] = useState([]);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
  const [atual, setAtual] = useState(0);
  const [totalItens, setTotalItens] = useState(0);

  useEffect(() => {
    async function dadosCorpos() {
      //Essa linha de baixo pega o token de autenticação do localStorage
      const token = localStorage.getItem("token");

      //Essa de baixo, faz a chamada GET pra rota /api/linha, passando o token como cabeçalho e passa pra
      //uma variavel response
      const response = await api.get(`/api/pontofinal?pagina=${pagina}`, {
        headers: { Authorization: token },
      });

      //aqui pego do response.data que é onde tá os dados da linha e passo pra uma variavel tbm
      let dados = response.data;
      setAtual(dados.paginaAtual);
      setTotal(dados.totalPaginas);
      setTotalItens(dados.totalItens);

      let temp = [];

      dados.lista.forEach((item) => {
        temp.push(
          criaDados(
            item.id,
            item.nome,
            item.totalLinhas
          )
        );
      });
      setCorpo(temp);
    }

    dadosCorpos();
  }, [pagina]);

  function criaDados(id, nome, totalLinhas) {
    return { id, nome, totalLinhas };
  }

  const nova = () => {
    // abrir modal sweet alert e criar nova parada
  }

  const editar = () => {
    // abrir modal sweet alert e criar nova parada
    alert("asdf");
  };

  return (
    <Container>
      <Row>
        <Titulo textoMenor="consulta de Parada" textoMaior="" />
      </Row>

      <Acoes>
        <Botao descricao="Nova Parada" estiloEscuro={true} onClick={nova} />

        {/* <Botao descricao="relatório" url="/parada" /> */}
      </Acoes>

      <Row>
        <Tabela tipo="pontofinal" dados={corpo} detalhes={false} editarFuncao={editar} />
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
