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
  const url = "/api/administrador";
  const urlEdicao = `${url}/${id}`;

  const validation = yup.object({
    nome: yup.string().required(),
    conta: yup.object({
      senha: yup.string().required(),
      email: yup.string().required(),
    }),
    confirmarSenha: yup
      .string()
      .required()
      .test("passwords-match", "As senhas devem ser iguais", function (value) {
        return this.parent.conta.senha === value;
      }),
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
    isEdicao
      ? editar(urlEdicao, values, history)
      : cadastrar(url, values, history);
  };

  const Step = ({ children }) => children;

  return (
    <Container>
      {isEdicao && Object.keys(dados).length === 0 ? (
        <Loader />
      ) : (
        <MultiStepForm
          titulo={`${tipoPagina} do administrador`}
          initialValues={dados}
          onSubmit={onSubmit}
        >
          <Step titulo="DADOS DE ACESSO" validationSchema={validation}>
            <InputFormik texto="Nome" maxLength="100" name="nome" type="text" />

            <InputFormik texto="Email" name="conta.email" type="email" />

            <InputFormik
              texto="Senha"
              maxLength="255"
              name="conta.senha"
              type="password"
              textoAlerta="Sua senha deve conter no mínimo 8 letras."
            />

            <InputFormik
              texto="Confirmar Senha"
              maxLength="255"
              name="confirmarSenha"
              type="password"
            />
          </Step>
        </MultiStepForm>
      )}
    </Container>
  );
}
