import React, { useEffect, useState } from "react";
import api from "../../services/api";
//Então, é que agora tá basicamente "pronta"

import { Container, Row } from "./styles";
import Tabela from "../../components/Tabela2";
import Paginacao from "../../components/Paginacao";
import Swal from "sweetalert2";
import Loader from "./../../components/Loader";
import CabecalhoConsulta from "../../components/CabecalhoConsulta";

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
        temp.push(criaDados(item.id, item.nome, item.totalLinhas));
      });
      setCorpo(temp);
    }

    dadosCorpos();
  }, [pagina]);

  function criaDados(id, nome, total_de_linhas) {
    return { id, nome, total_de_linhas };
  }

  const nova = async () => {
    let result = await Swal.fire({
      title: "Nome da nova parada",
      input: "text",
      showCancelButton: true,
      cancelButtonText: "Cancelar",
      confirmButtonText: "Cadastrar",
    });

    if (result.value) {
      try {
        const token = localStorage.getItem("token");
        const response = await api.post(
          `/api/pontofinal`,
          { nome: result.value },
          {
            headers: { Authorization: token },
          }
        );

        if (response.status === 201) {
          Swal.fire("Sucesso!", "A nova parada foi cadastrada.", "success");
          window.location.reload();
        } else {
          Swal.fire("Erro!", "Tente novamente.", "error");
        }
      } catch (e) {
        Swal.fire("Erro!", "Tente novamente.", "error");
      }
    }
  };

  const editar = async (id) => {
    let result = await Swal.fire({
      title: "Edição da Parada",
      input: "text",
      showCancelButton: true,
      cancelButtonText: "Cancelar",
      confirmButtonText: "Alterar",
    });

    if (result.value) {
      try {
        const token = localStorage.getItem("token");
        const response = await api.put(
          `/api/pontofinal/${id}`,
          { nome: result.value },
          {
            headers: { Authorization: token },
          }
        );

        if (response.status === 200) {
          Swal.fire("Sucesso!", "A parada foi alterada.", "success");
          window.location.reload();
        } else {
          Swal.fire("Erro!", "Tente novamente.", "error");
        }
      } catch (e) {
        Swal.fire("Erro!", "Tente novamente.", "error");
      }
    }
  };

  return corpo.length <= 0 ? (
    <Loader />
  ) : (
    <Container>
      <CabecalhoConsulta
        titulo="Paradas"
        onClick={nova}
        totalItens={totalItens}
        botaoTitulo="Nova parada"
      />

      <Row>
        <Tabela
          tipo="pontofinal"
          dados={corpo}
          detalhes={false}
          editarFuncao={editar}
        />
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
