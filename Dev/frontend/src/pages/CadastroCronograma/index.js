/* eslint react-hooks/exhaustive-deps: 0 */
import React, { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import * as yup from "yup";
import { cadastrar, editar, consultarEdicao } from "../../services/metodos";
import api from "../../services/api";

import ComboBoxComRotulo from "../../components/ComboBoxComRotulo";
import MultiStepForm from "../../components/MultiStepForm";
import Botao from "../../components/Botao";
import Loader from "../../components/Loader";
import InputFormik from "../../components/InputFormik";
import { Container, CustomSelect } from "./styles";

export default function CadastroAdmin(props) {
  const [dados, setDados] = useState({});
  const [horarios, setHorarios] = useState([]);

  const history = useHistory();
  const caminho = props.match.path;
  const id = props.match.params.id;

  const isEdicao = caminho.includes("editar");
  const tipoPagina = isEdicao ? "Edição" : "Cadastro";
  const url = "/api/cronograma";
  const urlEdicao = `${url}/${id}`;

  const validation = yup.object({
    dataCronograma: yup.date(),
    linhaId: yup.number().required(),
    horarios: yup.array(),
  });

  function addHorario() {
    console.log("ola");
    let hora = Object.assign([], horarios);
    hora.push(0);
    setHorarios(hora);
    console.log(horarios);
  }

  function removeHorario() {
    console.log("ola");
    let hora = Object.assign([], horarios);
    hora.pop();
    setHorarios(hora);
    console.log(horarios);
  }

  useEffect(() => {
    setHorarios([0, 1, 2, 3, 4, 5]);
  }, []);

  useEffect(() => {
    async function consulta() {
      const retorno = await consultarEdicao(urlEdicao);
      retorno.conta.senha = "";
      setDados(retorno);
    }
    if (isEdicao) {
      consulta();
    }
  }, [id]);

  const onSubmit = (values) => {
    values.horarios.forEach((item) => {
      item.horaPrevistaSaida = `${values.dataCronograma}T${
        item.horaPrevistaSaida || "00:00"
      }`;
      item.horaPrevistaChegada = `${values.dataCronograma}T${
        item.horaPrevistaChegada || "00:00"
      }`;
    });

    isEdicao
      ? editar(urlEdicao, values, history)
      : cadastrar(url, values, history);
  };

  // https://github.com/nadbm/react-datasheet
  // https://adazzle.github.io/react-data-grid/canary/?path=/story/demos--common-features

  const Step = ({ children }) => children;

  const [local, setLocal] = useState("");
  const [tipoPesquisa, setTipoPesquisa] = useState("");

  const comboLinha = [
    { texto: "Pesquisar por:", value: 0 },
    { texto: "Número da Linha", value: 1 },
    { texto: "Parada Inicial", value: 2 },
    { texto: "Parada Final", value: 3 },
  ];

  const pesquisarOpcoes = async (inputValue) => {
    const token = localStorage.getItem("token");
    let url = "linha";
    let rotaAPI = "";

    rotaAPI = `/api/${url}?q=${inputValue}`;

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

    const response = await api.get(rotaAPI, {
      headers: { Authorization: token },
    });

    let options = response.data.map((item) => {
      let label = item.numero || item.nome;

      label = `${item.numero} - ${item.pontoIda.nome} / ${item.pontoVolta.nome}`;

      return {
        value: item.id,
        label: label,
        dados: item,
      };
    });

    return options;
  };

  return (
    <Container>
      {isEdicao && Object.keys(dados).length === 0 ? (
        <Loader />
      ) : (
        <MultiStepForm
          titulo={`${tipoPagina} cronograma`}
          initialValues={dados}
          onSubmit={onSubmit}
        >
          <Step titulo="DADOS">
            <InputFormik texto="Data" name="dataCronograma" type="date" />
            <InputFormik texto="Linha" name="linhaId" type="number" />

            <ComboBoxComRotulo
              conteudoCombo={comboLinha}
              stateSelecionado={tipoPesquisa}
              pequeno={true}
              onchange={(e) => setTipoPesquisa(e.target.value)}
            />

            <CustomSelect
              placeholder="Digite aqui o termo de sua pesquisa..."
              value={local}
              onChange={(e) => setLocal(e)}
              loadOptions={(e) => pesquisarOpcoes(e)}
            />
            <table>
              <thead>
                <tr>
                  <td>Horario saida</td>
                  <td>Horario chegada</td>
                  <td>Motorista</td>
                </tr>
              </thead>
              <tbody>
                {horarios.map((item, index) => (
                  <tr key={index}>
                    <td>
                      <InputFormik
                        texto=""
                        name={`horarios[${index}].horaPrevistaSaida`}
                        type="time"
                        tamanho={150}
                      />
                    </td>
                    <td>
                      <InputFormik
                        texto=""
                        name={`horarios[${index}].horaPrevistaChegada`}
                        type="time"
                        tamanho={150}
                      />
                    </td>
                    <td>
                      <InputFormik
                        texto=""
                        name={`horarios[${index}].motoristaId`}
                        type="text"
                        tamanho={150}
                      />
                    </td>
                  </tr>
                ))}

                <div>
                  <Botao
                    descricao={"Adicionar horário"}
                    estiloEscuro={true}
                    type="button"
                    onClick={addHorario}
                  />
                  <Botao
                    descricao={"Remover horário"}
                    estiloEscuro={true}
                    type="button"
                    onClick={removeHorario}
                  />
                </div>
              </tbody>
            </table>
          </Step>
        </MultiStepForm>
      )}
    </Container>
  );
}
