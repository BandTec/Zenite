import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import api from "../../services/api";
import Swal from "sweetalert2";
import AsyncSelect from "react-select/async";

import {
  Container,
  CorpoPagina,
  FormContainer,
  Titulo,
  Subtitulo,
  CaixaHorizontal,
  Caixa,
  Rotulo,
} from "./styles";

import ComboBoxComRotulo from "../../components/ComboBoxComRotulo";
import BotaoForm from "../../components/BotaoForm";
import InputComRotulo from "../../components/InputComRotulo";

export default function CadastroOnibus(props) {
  const [numero, setNumero] = useState("");
  const [placa, setPlaca] = useState("");
  const [modelo, setModelo] = useState("");
  const [fabricante, setFabricante] = useState("");
  const [acessivel, setAcessivel] = useState("");
  const [qtdPassageirosSentados, setQtdPassageirosSentados] = useState("");
  const [qtdPassageirosEmPe, setQtdPassageirosEmPe] = useState("");
  const [gerente, setGerente] = useState("");

  const caminho = props.match.path;
  const id = props.match.params.id;
  const isEdicao = caminho.includes("editar");
  const tipoPagina = isEdicao ? "Edição" : "Cadastro";

  const cadastro = async () => {
    let corpo = {
      numero: numero,
      placa: placa,
      modelo: modelo,
      fabricante: fabricante,
      acessivel: acessivel === 1 ? true : false,
      qtdPassageirosSentados: qtdPassageirosSentados,
      qtdPassageirosEmPe: qtdPassageirosEmPe,
      gerente: { id: gerente.dados.id },
    };

    try {
      const token = await localStorage.getItem("token");

      const response = await api.post("/api/onibus", corpo, {
        headers: { Authorization: token },
      });

      if (response.status === 201) {
        props.history.push("/onibus");
        Swal.fire({
          icon: "success",
          title: "Cadastrado com Sucesso",
          showConfirmButton: false,
          timer: 2000,
        });
      } else {
        console.log(response);
      }
    } catch (e) {
      console.log(e);
      Swal.fire({
        title: "Tente novamente",
        text: "Ocorreu um imprevisto, por gentileza tente novamente.",
        icon: "error",
        showConfirmButton: false,
      });
    }
  };

  useEffect(() => {
    async function consultarEdicao() {
      try {
        const token = localStorage.getItem("token");
        const response = await api.get(`/api/onibus/${id}`, {
          headers: { Authorization: token },
        });

        const dados = response.data;

        setNumero(dados.numero);
        setPlaca(dados.placa);
        setModelo(dados.modelo);
        setFabricante(dados.fabricante);
        setAcessivel(dados.acessivel ? 1 : 2);
        setQtdPassageirosSentados(dados.qtdPassageirosSentados);
        setQtdPassageirosEmPe(dados.qtdPassageirosEmPe);
        if (dados.gerente) {
          setGerente(option(dados.gerente));
        }
      } catch (e) {
        Swal.fire({
          title: "Tente novamente",
          text: "Ocorreu um imprevisto, por gentileza tente novamente.",
          icon: "error",
          showConfirmButton: false,
        });
      }
    }
    if (id) consultarEdicao();
  }, [id]);

  const option = (e) => {
    return {
      value: e.id,
      label: e.nome,
      dados: e,
    };
  };

  const editar = async () => {
    let corpo = {
      id: id,
      numero: numero,
      placa: placa,
      modelo: modelo,
      fabricante: fabricante,
      acessivel: acessivel === 1 ? true : false,
      qtdPassageirosSentados: qtdPassageirosSentados,
      qtdPassageirosEmPe: qtdPassageirosEmPe,
      gerente: { id: gerente.dados.id },
    };

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
        const token = await localStorage.getItem("token");

        const response = await api.put(`/api/onibus/${id}`, corpo, {
          headers: { Authorization: token },
        });

        if (response.status === 200) {
          props.history.push("/onibus");
          Swal.fire({
            position: "flex-end",
            icon: "success",
            title: "Alterado com Sucesso",
            showConfirmButton: false,
          });
          window.location.reload();
        } else {
          alert("Ocorreu um erro. Tente de novo");
        }
      } catch (e) {
        Swal.fire({
          title: "Tente novamente",
          text: "Ocorreu um imprevisto, por gentileza tente novamente.",
          icon: "error",
          showConfirmButton: false,
        });
      }
    }
  };

  const pesquisa = async (inputValue) => {
    const token = localStorage.getItem("token");

    const response = await api.get(`/api/gerente?q=${inputValue}`, {
      headers: { Authorization: token },
    });

    let opcoes = response.data.map((item) => ({
      value: item.id,
      label: item.nome,
      dados: item,
    }));

    return opcoes;
  };

  const acessivelOpcoes = [
    { value: 1, texto: "SIM" },
    { value: 2, texto: "NÃO" },
  ];

  const concluir = () => {
    isEdicao ? editar() : cadastro();
  };

  return (
    <Container>
      <CorpoPagina>
        <FormContainer>
          <Link to="/onibus">
            <BotaoForm texto="VOLTAR" ladoDireito={false} />
          </Link>

          <Caixa>
            <Subtitulo>{tipoPagina} DO ÔNIBUS</Subtitulo>
            <Titulo>Dados</Titulo>

            <InputComRotulo
              texto="NÚMERO DO ÔNIBUS"
              maxLength="10"
              name="numeroOnibus"
              type="text"
              value={numero}
              onChange={(e) => setNumero(e.target.value)}
              required
            />

            <CaixaHorizontal>
              <InputComRotulo
                pequeno={true}
                texto="Placa"
                value={placa}
                onChange={(e) => setPlaca(e.target.value)}
                required
              />

              <InputComRotulo
                pequeno={true}
                texto="Modelo"
                required
                value={modelo}
                onChange={(e) => setModelo(e.target.value)}
              />
            </CaixaHorizontal>

            <CaixaHorizontal>
              <InputComRotulo
                pequeno={true}
                texto="Fabricante"
                value={fabricante}
                onChange={(e) => setFabricante(e.target.value)}
                required
              />

              <ComboBoxComRotulo
                texto="Acessível"
                conteudoCombo={acessivelOpcoes}
                pequeno={true}
                stateSelecionado={acessivel}
                onchange={(e) => setAcessivel(e.target.value)}
              />
            </CaixaHorizontal>

            <CaixaHorizontal>
              <InputComRotulo
                type="number"
                pequeno={true}
                texto="Passageiros Sentados"
                value={qtdPassageirosSentados}
                min="2"
                max="3"
                onChange={(e) => setQtdPassageirosSentados(e.target.value)}
              />

              <InputComRotulo
                type="number"
                pequeno={true}
                texto="Passageiros em Pé"
                value={qtdPassageirosEmPe}
                min="2"
                max="3"
                onChange={(e) => setQtdPassageirosEmPe(e.target.value)}
              />
            </CaixaHorizontal>

            <Rotulo>Gerente</Rotulo>
            <AsyncSelect
              value={gerente}
              onChange={(e) => setGerente(e)}
              loadOptions={(e) => pesquisa(e)}
            />
          </Caixa>

          <BotaoForm texto="Finalizar" concluir={true} criarJson={concluir} />
        </FormContainer>
      </CorpoPagina>
    </Container>
  );
}
