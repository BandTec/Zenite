import React, { useEffect, useState } from "react";
import consultar from "../../services/metodos/consultar";
import api from "../../services/api";

import { Container, Row } from "./styles";
import Tabela from "./../../components/Tabela2";
import Paginacao from "../../components/Paginacao";
import Loader from "./../../components/Loader";
import CabecalhoConsulta from "../../components/CabecalhoConsulta";

export default function ConsultaLinha() {
  const [corpo, setCorpo] = useState([]);
  const [loading, setLoading] = useState(true);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
  const [totalItens, setTotalItens] = useState(0);

  useEffect(() => {
    async function consultarFiscais() {
      const url = `/api/linha?pagina=${pagina}`;
      const resultado = await consultar(url, criaDados);
      setCorpo(resultado.dados);
      setTotal(resultado.totalPaginas);
      setTotalItens(resultado.totalItens);
      setLoading(false);
    }

    consultarFiscais();
  }, [pagina]);

  function criaDados(item) {
    const { id, numero, pontoIda, pontoVolta, carrosId } = item;
    return {
      id,
      numero,
      parada_inicial: pontoIda.nome,
      parada_final: pontoVolta.nome,
      quantidade_de_onibus: carrosId.length,
    };
  }

  const exportarDados = async () => {
    const token = localStorage.getItem("token");
    const response = await api.get(`/api/exportacao/linha`, {
      headers: { Authorization: token },
    });
    const url = window.URL.createObjectURL(new Blob([response.data]));
    const link = document.createElement("a");
    link.href = url;
    link.setAttribute("download", "zenite_linhas.txt");
    document.body.appendChild(link);
    link.click();
  };

  const importarDados = async () => {
    const inputId = document.getElementById("inputFileB");
    inputId.click();
  };

  const importarFile = (event) => {
    const token = localStorage.getItem("token");
    const teste = event.target.files[0];

    const formData = new FormData();

    formData.append("txt", teste);

    api.post(`/api/importacao/linha`, formData, {
      headers: {
        Authorization: token,
        "Content-Type": "multipart/form-data",
      },
    });
  };

  return corpo.length <= 0 && loading ? (
    <Loader />
  ) : (
    <Container>
      <CabecalhoConsulta
        botaoTitulo="Nova Linha"
        titulo="Linha"
        url="linha"
        totalItens={totalItens}
        importarFile={importarFile}
        importarOnClick={importarDados}
        importarTitle={"Importar dados"}
        exportarOnclick={exportarDados}
        exportarTitle={"Exportar dados"}
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
