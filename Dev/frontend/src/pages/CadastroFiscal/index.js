/* eslint react-hooks/exhaustive-deps: 0 */
import React, { useState, useEffect } from "react";
import {
  validationStep1,
  validationStep2,
  validationStep3,
} from "../../functions/Validadores/fiscalValidacao";
import { cadastrar, editar, consultarEdicao } from "../../services/metodos";
import { useHistory } from "react-router-dom";

import MultiStepForm from "../../components/MultiStepForm";
import Loader from "../../components/Loader";
import InputFormik from "../../components/InputFormik";
import { Container, CaixaHorizontal } from "./styles";

export default function CadastroFiscal(props) {
  const [dados, setDados] = useState({});
  const caminho = props.match.path;
  const id = props.match.params.id;
  const isEdicao = caminho.includes("editar");
  const tipoPagina = isEdicao ? "Edição" : "Cadastro";
  const history = useHistory();
  const url = "/api/fiscal";
  let urlEdicao = `${url}/${id}`;

  useEffect(() => {
    async function consulta() {
      const retorno = await consultarEdicao(urlEdicao);
      delete retorno["linhas"];
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
          titulo={`${tipoPagina} do fiscal`}
          initialValues={dados}
          onSubmit={onSubmit}
        >
          <Step titulo="DADOS CADASTRAIS" validationSchema={validationStep1}>
            <InputFormik texto="Nome" name="nome" maxLength="100" />
            <InputFormik texto="CPF" mask="999.999.999-99" name="cpf" />

            <CaixaHorizontal>
              <InputFormik
                pequeno={true}
                texto="Data de Nascimento"
                type="date"
                name="dataNascimento"
              />
              <InputFormik
                texto="Celular"
                name="numeroTelefone"
                // mask="(99) 9 9999-9999"
                pequeno={true}
                maxLength="15"
              />
            </CaixaHorizontal>
          </Step>

          <Step titulo="Endereço" validationSchema={validationStep2}>
            <InputFormik texto="CEP" name="endereco.cep" mask="99999-99" />

            <InputFormik
              texto="Logradouro"
              maxLength="120"
              name="endereco.logradouro"
            />

            <CaixaHorizontal>
              <InputFormik
                pequeno={true}
                texto="Número"
                maxLength="16"
                name="endereco.numero"
              />

              <InputFormik
                texto="Complemento"
                pequeno={true}
                maxLength="60"
                name="endereco.complemento"
              />
            </CaixaHorizontal>

            <CaixaHorizontal>
              <InputFormik
                pequeno={true}
                texto="Cidade"
                maxLength="40"
                name="endereco.cidade"
              />

              <InputFormik
                texto="Estado"
                pequeno={true}
                maxLength="2"
                name="endereco.estado"
              />
            </CaixaHorizontal>
          </Step>

          <Step titulo="DADOS DE ACESSO" validationSchema={validationStep3}>
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
