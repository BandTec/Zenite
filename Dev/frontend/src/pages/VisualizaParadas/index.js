import React, { useEffect, useState } from "react";
import consultar from "../../services/metodos/consultar";

import api from "../../services/api";

import { Container, Row } from "./styles";
import Tabela from "../../components/Tabela2";
import Paginacao from "../../components/Paginacao";
import Swal from "sweetalert2";
import Loader from "./../../components/Loader";
import CabecalhoConsulta from "../../components/CabecalhoConsulta";

export default function ConsultaParada() {
  const [corpo, setCorpo] = useState([]);
  const [loading, setLoading] = useState(true);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
  const [totalItens, setTotalItens] = useState(0);

  useEffect(() => {
    async function consultarParadas() {
      const url = `/api/pontofinal?pagina=${pagina}`;
      const resultado = await consultar(url, criaDados);
      setCorpo(resultado.dados);
      setTotal(resultado.totalPaginas);
      setTotalItens(resultado.totalItens);
      setLoading(false);
    }

    consultarParadas();
  }, [pagina]);

  function criaDados(item) {
    const { id, nome, totalLinhas } = item;
    return { id, nome, total_de_linhas: totalLinhas };
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

  return corpo.length <= 0 && loading ? (
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
          pgAtual={pagina}
          totalPg={total}
          mudarPag={(p) => setPagina(p)}
          totalItens={totalItens}
        />
      </Row>
    </Container>
  );
}
