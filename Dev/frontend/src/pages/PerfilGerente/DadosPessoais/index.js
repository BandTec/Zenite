import React, { useState, useEffect } from "react";
import { cpfMask, telefoneMask, /* dataMask */ } from "./../../../functions/Mascaras/mask";
import { CaixaHorizontal, Titulo} from './../styles';
import InputComRotulo from './../../../components/InputComRotulo';

export default function DadosPessoais({ adicionarDados, dados }) {
  
  const [nome, setNome ] = useState("");
  const [valorCpf, setValorCpf] = useState("");
  const [valorData, setValorData] = useState("");
  const [valorTelefone, setValorTelefone] = useState("");

  const mascararCpf = e => {
    setValorCpf(cpfMask(e.target.value));
  }

  const mascararData = e => {
    setValorData(e.target.value);
  }

  const mascararTelefone = e => {
    setValorTelefone(telefoneMask(e.target.value));
  }

    useEffect(() => {
      
      if (Object.keys(dados).length !== 0) {
        setValorCpf(dados.cpf);
        setNome(dados.nome);
        setValorData(dados.dataNascimento);
        setValorTelefone(dados.numeroTelefone);
      }
    }, []);

 useEffect(() => {
   adicionarDados({
     nome,
     cpf: valorCpf,
     dataNascimento: valorData,
     numeroTelefone: valorTelefone,
   });
 }, [valorCpf, valorData, valorTelefone, nome]);

  return (
    <>
      <Titulo>Dados de Pessoais</Titulo>
      <InputComRotulo
        value={nome}
        onChange={(e) => setNome(e.target.value)}
        texto="Nome"
        name="nome"
        maxLength="100"
        required
      />
      <InputComRotulo
        texto="CPF"
        maxLength="14"
        name="cpf"
        value={valorCpf}
        onChange={mascararCpf}
        required
      />
  
      <CaixaHorizontal>
        <InputComRotulo
          pequeno={true}
          texto="Data de Nascimento"
          maxLength="10"
          name="datadenascimento"
          value={valorData}
          onChange={mascararData}
          required
        />
        <InputComRotulo
          texto="Telefone"
          pequeno={true}
          maxLength="10"
          name="telefone"
          value={valorTelefone}
          onChange={mascararTelefone}
          required
        />
      </CaixaHorizontal>
    </>
  );
}
