import React, { useState } from 'react';

import { cepMask } from "./../../../functions/Mascaras/mask";
import { Container, CaixaHorizontal, CorpoPagina, FormContainer, Titulo, Subtitulo, Caixa } from './styles';

import BotaoForm from './../../../components/BotaoForm';
import StatusPage from './../../../components/StatusPage';
import InputComRotulo from './../../../components/InputComRotulo';

export default function DadosEndereco({ mudarPagina, tipoPagina, adicionarDados }) {
  const [valorCep, setValorCep] = useState("");
  const [logradouro, setLogradouro] = useState("");
  const [numero, setNumero] = useState("");
  const [complemento, setComplemento] = useState("");
  const [cidade, setCidade] = useState("");
  const [estado, setEstado] = useState("");

  const mascararCep = e => {
    setValorCep(e.target.value);
  }

  const criarJson = () => {
    adicionarDados({
      "endereco": {
      "cep": valorCep,
      logradouro,
      numero,
      complemento,
      cidade,
      estado
    }
    })
  }

  return (
    <Container>

      <CorpoPagina>
        <CaixaHorizontal center={true}>
          <StatusPage
            ativo={false}
            texto="Dados Pessoais"
            temProximoPasso={true}
          />

          <StatusPage 
            ativo={true} 
            texto="Endereço" 
            temProximoPasso={true} 
          />

          <StatusPage
            ativo={false}
            texto="Dados de Acesso"
            temProximoPasso={false}
          />
        </CaixaHorizontal>

        <FormContainer>
          <BotaoForm
            texto="Voltar"
            ladoDireito={false}
            mudarPagina={mudarPagina}
          />

          <Caixa>
            <Subtitulo>{tipoPagina} DO FISCAL</Subtitulo>
            <Titulo>Endereço</Titulo>

            <InputComRotulo
              texto="CEP"
              maxLength="9"
              name="cep"
              value={valorCep}
              onChange={mascararCep}
              required
            />

            <InputComRotulo
              texto="Logradouro"
              maxLength="120"
              name="logradouro"
              value={logradouro}
              onChange={event => setLogradouro(event.target.value)}
              required
            />

            <CaixaHorizontal>
              <InputComRotulo
                pequeno={true}
                texto="Número"
                maxLength="16"
                name="numero"
                value={numero}
                onChange={event => setNumero(event.target.value)}
                required
              />

              <InputComRotulo
                texto="Complemento"
                pequeno={true}
                maxLength="60"
                name="complemento"
                value={complemento}
                onChange={event => setComplemento(event.target.value)}
              />
            </CaixaHorizontal>

            <CaixaHorizontal>
              <InputComRotulo
                pequeno={true}
                texto="Cidade"
                maxLength="40"
                name="cidade"
                value={cidade}
                onChange={event => setCidade(event.target.value)}
              />

              <InputComRotulo
                texto="Estado"
                pequeno={true}
                maxLength="2"
                name="estado"
                value={estado}
                onChange={event => setEstado(event.target.value)}
              />
            </CaixaHorizontal>
          </Caixa>

          <BotaoForm texto="Próximo" mudarPagina={mudarPagina} criarJson={criarJson}/>
        </FormContainer>
      </CorpoPagina>
    </Container>
  );
}
