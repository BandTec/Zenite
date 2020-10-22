/* eslint react-hooks/exhaustive-deps: 0 */
import React, { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import * as yup from "yup";
import { cadastrar, editar, consultarEdicao } from "../../services/metodos";

import MultiStepForm from "../../components/MultiStepForm";
import Loader from "../../components/Loader";
import InputFormik from "../../components/InputFormik";
import { Container } from "./styles";

export default function CadastroAdmin(props) {
  const [dados, setDados] = useState({});

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
    horarios: yup.string(),
  });

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
    values.horarios = [
      {
        horaPrevistaSaida: new Date(),
        horaPrevistaChegada: new Date(),
        motoristaId: 5,
      },
    ];

    console.log(values);
    isEdicao
      ? editar(urlEdicao, values, history)
      : cadastrar(url, values, history);
  };

  // https://github.com/nadbm/react-datasheet
  // https://adazzle.github.io/react-data-grid/canary/?path=/story/demos--common-features

  const Step = ({ children }) => children;

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

            <InputFormik texto="hora" name="hora" type="time" />
          </Step>
        </MultiStepForm>
      )}
    </Container>
  );
}
