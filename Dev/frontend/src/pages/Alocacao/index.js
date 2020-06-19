import React, { useState, useEffect } from "react";
import api from "../../services/api";

import Swal from "sweetalert2";
import {
  Container,
  Nav,
  Row,
  Col,
  Titulo,
  Subtitulo,
  Texto,
  CustomSelect,
  InfoTitle,
} from "./styles";
import Botao from "../../components/Botao";
import ComboBoxComRotulo from "../../components/ComboBoxComRotulo";
import Detalhes from "./Detalhes";

export default function Alocacao() {
  const [selecionado, setSelecionado] = useState("");
  const [alocacao, setAlocacao] = useState("Escolha");
  const [alocar, setAlocar] = useState("");
  const [local, setLocal] = useState("");
  const [tipoPesquisa, setTipoPesquisa] = useState("");
  let url = "";
  let corpo_requisicao = {};

  const comboLinha = [
    { texto: "Pesquisar por:", value: 0 },
    { texto: "Número da Linha", value: 1 },
    { texto: "Parada Inicial", value: 2 },
    { texto: "Parada Final", value: 3 },
  ];

  const trocarAlocacao = (e) => {
    setSelecionado(e.target.value);
  };

  useEffect(() => {
    setAlocar("");
    setLocal("");
    if (selecionado === "Fiscal" || selecionado === "Ônibus") {
      setAlocacao("Linha");
    } else if (selecionado === "Motorista") {
      setAlocacao("Ônibus");
    } else {
      setAlocacao("Escolha");
    }
  }, [selecionado]);

  const pesquisarOpcoes = async (inputValue, rota) => {
    const token = localStorage.getItem("token");
    let url = rota.toLowerCase();
    let rotaAPI = "";

    if (url === "ônibus") {
      url = "onibus";
    }

    rotaAPI = `/api/${url}?q=${inputValue}`;

    if (rota === "Linha") {
      let tipo = "";

      switch (tipoPesquisa) {
        case "1":
          tipo = "numero";
          break;
        case "2":
          tipo = "paradaInicial";
          break;
        case "3":
          tipo = "paradaFinal";
          break;
        default:
          tipo = "numero";
          break;
      }

      rotaAPI = `/api/${url}?${tipo}=${inputValue}`;
    }

    const response = await api.get(rotaAPI, {
      headers: { Authorization: token },
    });

    let options = response.data.map((item) => {
      let label = item.numero || item.nome;
      if (rota === "Linha") {
        label = `${item.numero} - ${item.pontoIda.nome} / ${item.pontoVolta.nome}`;
      }
      return {
        value: item.id,
        label: label,
        dados: item,
      };
    });

    return options;
  };

  const configurar = () => {
    switch (selecionado) {
      case "Fiscal":
        url = "/api/fiscal/linhas";
        corpo_requisicao = {
          idFiscal: alocar.dados.id,
          idLinha: local.dados.id,
        };
        break;
      case "Motorista":
        url = "/api/motorista/onibus";
        corpo_requisicao = {
          idMotorista: alocar.dados.id,
          idCarro: local.dados.id,
        };
        break;
      case "Ônibus":
        url = "/api/onibus/linhas";
        corpo_requisicao = {
          idLinha: local.dados.id,
          idCarro: alocar.dados.id,
        };
        break;
      default:
        Swal.fire({
          title: "Por favor escolha os dados a serem alocados",
          confirmButtonText: "Ok",
        });
        return;
    }
  };

  const salvar = async () => {
    if (!alocar.dados || !local.dados) {
      Swal.fire({
        title: "Por favor escolha os dados a serem alocados",
        confirmButtonText: "Ok",
      });
      return;
    }

    configurar();

    if (!!url && !!corpo_requisicao) {
      try {
        const token = localStorage.getItem("token");

        const response = await api.post(url, corpo_requisicao, {
          headers: { Authorization: token },
        });

        if (response.status === 201) {
          Swal.fire("Sucesso!", "Alocado com sucesso.", "success");
          atualizar();
        } else {
          Swal.fire("Erro!", "Tente novamente.", "error");
        }
      } catch (erro) {
        console.log(erro);
        Swal.fire("Erro!", "Tente novamente.", "error");
      }
    }
  };

  const encurtarNome = (e) => {
    if (e.length > 15) {
      return e.substring(0, e.indexOf(" "));
    }
    return e;
  };

  const atualizar = () => {
    switch (selecionado) {
      case "Fiscal":
        let linhas = alocar.dados.linhas;
        linhas.push(local.dados);
        setAlocar({ ...alocar, dados: { ...alocar.dados, linhas: linhas } });

        setLocal({
          ...local,
          dados: { ...local.dados, fiscal: alocar.dados.nome },
        });
        break;
      case "Motorista":
        setAlocar({
          ...alocar,
          dados: { ...alocar.dados, carro: local.dados },
        });

         setLocal({
           ...local,
           dados: { ...local.dados, motorista: alocar.dados.nome },
         });
        break;
      case "Ônibus":
        let linhaAtrelado = `${local.dados.numero} - ${local.dados.pontoIda.nome} / ${local.dados.pontoVolta.nome}`;
        setAlocar({
          ...alocar,
          dados: { 
            ...alocar.dados, 
            linha: linhaAtrelado
          },
        });

        let carrosLista = local.dados.carros;
        carrosLista.push(alocar.dados.numero + " - " + alocar.dados.placa);
        setLocal({
          ...local,
          dados: { ...local.dados, carros: carrosLista },
        });
        break;
        default:
          break;
    }
  };

  const atualizarDado = (index) => {
    let linhas = alocar.dados.linhas;
    let linhaid = linhas[index].id;
    linhas.pop(index);
    setAlocar({ ...alocar, dados: { ...alocar.dados, linhas: linhas } });

    if(local.dados.id === linhaid){
     setLocal({
       ...local,
       dados: { ...local.dados, fiscal: "" },
     });
    }
  }

  return (
    <Container>
      <div>
        <Subtitulo>Cadastro</Subtitulo>
        <Titulo>Alocacao</Titulo>
      </div>
      <Nav>
        <div>
          <Texto>Alocar</Texto>
          <select value={selecionado} onChange={trocarAlocacao}>
            <option>Escolha</option>
            <option>Fiscal</option>
            <option>Motorista</option>
            <option>Ônibus</option>
          </select>
          <InfoTitle>{alocar.label && encurtarNome(alocar.label)}</InfoTitle>
        </div>
        <div>
          <Texto>A um{alocacao === "Linha" ? "a" : ""}</Texto>

          <Titulo>{alocacao}</Titulo>
          <InfoTitle>{local.label && encurtarNome(local.label)}</InfoTitle>
        </div>
        <Botao descricao="Alocar" onClick={salvar} />
      </Nav>

      {selecionado && (
        <Row>
          <Col>
            <CustomSelect
              placeholder="Digite aqui o termo de sua pesquisa..."
              value={alocar}
              onChange={(e) => setAlocar(e)}
              loadOptions={(e) => pesquisarOpcoes(e, selecionado)}
            />

            {!!alocar && (
              <Detalhes
                tipo={selecionado}
                objeto={alocar.dados}
                atualizarDado={atualizarDado}
              />
            )}
          </Col>

          <Col>
            <Row>
              {alocacao === "Linha" && (
                <ComboBoxComRotulo
                  conteudoCombo={comboLinha}
                  stateSelecionado={tipoPesquisa}
                  pequeno={true}
                  onchange={(e) => setTipoPesquisa(e.target.value)}
                />
              )}
              <CustomSelect
                placeholder="Digite aqui o termo de sua pesquisa..."
                value={local}
                onChange={(e) => setLocal(e)}
                loadOptions={(e) => pesquisarOpcoes(e, alocacao)}
              />
            </Row>

            {!!local && (
              <Detalhes
                tipo={alocacao}
                objeto={local.dados}
                atualizarDado={atualizarDado}
              />
            )}
          </Col>
        </Row>
      )}
    </Container>
  );
}
