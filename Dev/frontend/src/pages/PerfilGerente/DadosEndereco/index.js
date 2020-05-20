import React, { useState, useEffect } from "react";

// import { cepMask } from "./../../../functions/Mascaras/mask";
import { CaixaHorizontal, Titulo } from './../styles';
import InputComRotulo from './../../../components/InputComRotulo';

export default function DadosEndereco({ adicionarDados, dados }) {
  const [valorCep, setValorCep] = useState("");
  const [logradouro, setLogradouro] = useState("");
  const [numero, setNumero] = useState("");
  const [complemento, setComplemento] = useState("");
  const [cidade, setCidade] = useState("");
  const [estado, setEstado] = useState("");

  const mascararCep = e => {
    setValorCep(e.target.value);
  }

  useEffect(() => {

    if (Object.keys(dados).length !== 0) {
      setValorCep(dados.endereco.cep);
      setLogradouro(dados.endereco.logradouro);
      setNumero(dados.endereco.numero);
      setComplemento(dados.endereco.complemento);
      setCidade(dados.endereco.cidade);
      setEstado(dados.endereco.estado);
    }
  }, []);

  useEffect(() => {

    adicionarDados({
      endereco: {
        id: dados.endereco.id,
        cep: valorCep,
        logradouro,
        numero,
        complemento,
        cidade,
        estado,
      },
    });
  }, [valorCep, logradouro, numero, complemento, cidade, estado]);

  return (
    <>
      <Titulo>Dados de Endereço</Titulo>
      <InputComRotulo
        texto="CEP"
        maxLength="8"
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
        onChange={(event) => setLogradouro(event.target.value)}
        required
      />

      <CaixaHorizontal>
        <InputComRotulo
          pequeno={true}
          texto="Número"
          maxLength="16"
          name="numero"
          value={numero}
          onChange={(event) => setNumero(event.target.value)}
          required
        />

        <InputComRotulo
          texto="Complemento"
          pequeno={true}
          maxLength="60"
          name="complemento"
          value={complemento}
          onChange={(event) => setComplemento(event.target.value)}
        />
      </CaixaHorizontal>

      <CaixaHorizontal>
        <InputComRotulo
          pequeno={true}
          texto="Cidade"
          maxLength="40"
          name="cidade"
          value={cidade}
          onChange={(event) => setCidade(event.target.value)}
        />

        <InputComRotulo
          texto="Estado"
          pequeno={true}
          maxLength="2"
          name="estado"
          value={estado}
          onChange={(event) => setEstado(event.target.value)}
        />
      </CaixaHorizontal>
    </>
  );
}
