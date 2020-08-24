/* eslint react-hooks/exhaustive-deps: 0 */
import React, { useState, useEffect } from "react";
import api from "../../services/api";
import Swal from "sweetalert2";

import { Container } from "./styles";
import DadosPessoais from "./dadosPessoais";
import DadosEndereco from "./dadosEndereco";
import DadosAcesso from "./dadosAcesso";
import Loader from "./../../components/Loader";

export default function CadastroMotorista(props) {
  const [validacaoSenha, setValidacaoSenha] = useState(false);
  const [pagina, setPagina] = useState(1);
  const [dados, setDados] = useState({});
  const caminho = props.match.path;
  const id = props.match.params.id;
  const isEdicao = caminho.includes("editar");
  const tipoPagina = isEdicao ? "Edição" : "Cadastro";

  const mudarPagina = (isProximo) => {
    isProximo ? setPagina(pagina + 1) : setPagina(pagina - 1);
  };

  const adicionarDados = (novoDado) => {
    setDados({ ...dados, ...novoDado });
  };

  const mostrarSucesso = (mensagemCustomizada) => {
    let mensagemPadrao = "Registrado com sucesso";
    let mensagem = mensagemCustomizada ? mensagemCustomizada : mensagemPadrao;
    Swal.fire({
      title: "Sucesso!",
      text: mensagem,
      icon: "success",
      showConfirmButton: true,
    });
  };

  const mostrarErro = (mensagemCustomizada) => {
    let mensagemPadrao =
      "Ocorreu um imprevisto, por gentileza tente novamente.";
    let mensagem = mensagemCustomizada ? mensagemCustomizada : mensagemPadrao;
    Swal.fire({
      title: "Tente novamente",
      text: mensagem,
      icon: "error",
      showConfirmButton: false,
    });
  };

  useEffect(() => {
    async function consultarEdicao() {
      try {
        const token = localStorage.getItem("token");
        const response = await api.get(`/api/motorista/${id}`, {
          headers: { Authorization: token },
        });
        const dadosConsulta = response.data;
        delete dadosConsulta["carros"];
        setDados({ ...dadosConsulta });
      } catch (e) {
        mostrarErro();
      }
    }
    if (isEdicao) {
      consultarEdicao();
    }
  }, [id]);

  const cadastrar = async () => {
    try {
      const token = await localStorage.getItem("token");

      const response = await api.post("/api/motorista", dados, {
        headers: { Authorization: token },
      });

      if (response.status === 201) {
        mostrarSucesso("Motorista cadastrado com sucesso!")
        props.history.push("/motorista");
      }
    } catch (e) {
      alert("Ocorreu um erro. Tente de novo.");
    }
  };

  const editar = async () => {
    const token = await localStorage.getItem("token");
    let result = await Swal.fire({
      title: "Aviso",
      text: "Deseja realmente editar este dado? ",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Sim, desejo",
      cancelButtonText: "Não",
    });

    if (result.value) {
      try {
        if (validacaoSenha) {
          const response = await api.put(`/api/motorista/${id}`, dados, {
            headers: { Authorization: token },
          });

          if (response.status === 200) {
            mostrarSucesso("Motorista editado com sucesso!")
            props.history.push("/motorista");
          } else {
            mostrarErro();
          }
        } else {
          mostrarErro("Senha diferente, tente novamente.");
        }
      } catch (e) {
        mostrarErro();
      }
    }
  };

  const chamada = () => {
    isEdicao ? editar() : cadastrar();
  };

  return (
    <Container>
      {isEdicao && Object.keys(dados).length === 0 ? (
        <Loader />
      ) : pagina === 1 ? (
        <DadosPessoais
          mudarPagina={mudarPagina}
          tipoPagina={tipoPagina}
          adicionarDados={adicionarDados}
          dados={dados}
        />
      ) : pagina === 2 ? (
        <DadosEndereco
          mudarPagina={mudarPagina}
          tipoPagina={tipoPagina}
          adicionarDados={adicionarDados}
          dados={dados}
        />
      ) : (
        <>
          <DadosAcesso
            mudarPagina={mudarPagina}
            tipoPagina={tipoPagina}
            adicionarDados={adicionarDados}
            dados={dados}
            validarSenha={setValidacaoSenha}
          />
          {pagina >= 4 && chamada()}
        </>
      )}
    </Container>
  );
}
