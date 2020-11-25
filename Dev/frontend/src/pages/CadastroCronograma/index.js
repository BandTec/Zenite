/* eslint react-hooks/exhaustive-deps: 0 */
import React, { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import { cadastrar, editar, consultarEdicao } from "../../services/metodos";
import api from "../../services/api";

import ComboBoxComRotulo from "../../components/ComboBoxComRotulo";
import ComboBoxFormik from "../../components/ComboBoxFormik";
import MultiStepForm from "../../components/MultiStepForm";
import Botao from "../../components/Botao";
import Loader from "../../components/Loader";
import InputFormik from "../../components/InputFormik";
import { Container, CustomSelect, Row, Table, Rotulo } from "./styles";

export default function CadastroAdmin(props) {
  const [dados, setDados] = useState({});
  const [horarios, setHorarios] = useState([]);
  const [motoristas, setMotoristas] = useState([]);

  const history = useHistory();
  const caminho = props.match.path;
  const id = props.match.params.id;

  const isEdicao = caminho.includes("editar");
  const tipoPagina = isEdicao ? "Edição" : "Cadastro";
  const url = "/api/cronograma";
  const urlEdicao = `${url}/${id}`;

  const [linha, setLinha] = useState("");
  const [tipoPesquisa, setTipoPesquisa] = useState("");
  const [pesquisarMotoristas, setPesquisarMotorista] = useState(false);
  const pesquisarMoto = () => {
    setPesquisarMotorista(true);
  };

  const comboLinha = [
    { texto: "Pesquisar por:", value: 0 },
    { texto: "Número da Linha", value: 1 },
    { texto: "Parada Inicial", value: 2 },
    { texto: "Parada Final", value: 3 },
  ];

  function addHorario() {
    let hora = Object.assign([], horarios);
    hora.push(0);
    setHorarios(hora);
  }

  function removeHorario() {
    let hora = Object.assign([], horarios);
    hora.pop();
    setHorarios(hora);
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

  useEffect(() => {
    async function consulta() {
      const token = localStorage.getItem("token");
      const response = await api.get(`/api/motorista/linha/${linha.dados.id}`, {
        headers: { Authorization: token },
      });

      const dadosConsulta = response.data;
      let temp = [];
      dadosConsulta.forEach((item) => {
        temp.push({ value: item.motoristaId, texto: item.motorista });
      });
      setMotoristas(temp);
    }
    if (pesquisarMotoristas) {
      consulta();
    }
  }, [pesquisarMotoristas]);

  const onSubmit = (values) => {
    const data = values.dataCronograma.toString();

    let temp = Object.assign({}, values);
    temp.horarios.forEach((item) => {
      item.horaPrevistaSaida = `${data}T${item.horaPrevistaSaida || "00:00"}`;
      item.horaPrevistaChegada = `${data}T${
        item.horaPrevistaChegada || "00:00"
      }`;
    });
    temp.linhaId = linha.dados.id;
    temp.dataCronograma = `${temp.dataCronograma}T00:00:00`;
    // console.log(linha);
    console.log(temp);
    // console.log(values);

    isEdicao ? editar(urlEdicao, temp, history) : cadastrar(url, temp, history);
  };

  const Step = ({ children }) => children;

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

            <Rotulo>Escolha a linha</Rotulo>
            <Row>
              <ComboBoxComRotulo
                conteudoCombo={comboLinha}
                stateSelecionado={tipoPesquisa}
                pequeno={true}
                onchange={(e) => setTipoPesquisa(e.target.value)}
              />

              <CustomSelect
                placeholder="Digite aqui o termo de sua pesquisa..."
                value={linha}
                onChange={(e) => setLinha(e)}
                loadOptions={(e) => pesquisarOpcoes(e)}
              />
            </Row>

            {linha !== "" &&
              (linha?.dados?.fiscalId !== null ? (
                <>
                  <Rotulo>Fiscal da Linha</Rotulo>
                  <p>{linha?.dados?.fiscal}</p>
                  <Row>
                    <Botao
                      descricao={"Adicionar os horários"}
                      estiloEscuro={true}
                      type="button"
                      onClick={pesquisarMoto}
                    />
                  </Row>
                </>
              ) : (
                <>
                  <Rotulo>Fiscal da Linha</Rotulo>
                  <p>Essa linha não possuem fiscal alocado</p>
                </>
              ))}

            {pesquisarMotoristas && motoristas.length > 0 && (
              <>
                <Table>
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
                          <ComboBoxFormik
                            conteudoCombo={motoristas}
                            name={`horarios[${index}].motoristaId`}
                            // pequeno={true}
                          />
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </Table>

                <Row>
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
                </Row>
              </>
            )}
          </Step>
        </MultiStepForm>
      )}
    </Container>
  );
}
