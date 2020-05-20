import React, { useState, useEffect } from "react";
import { cpfMask, telefoneMask, dataMask, reformatarData, formatarData } from "./../../../functions/Mascaras/mask";
import { CaixaHorizontal, Titulo} from './../styles';
import InputComRotulo from './../../../components/InputComRotulo';

export default function DadosPessoais({ adicionarDados, dados }) {
  
  const [nome, setNome ] = useState("");
  const [valorCpf, setValorCpf] = useState("");
  const [valorData, setValorData] = useState("");
  const [valorTelefone, setValorTelefone] = useState("");
  const [cnh, setCnh] = useState("");
 const [registro, setRegistro] = useState("");

  const mascararCpf = e => {
    setValorCpf(cpfMask(e.target.value));
  }

  const mascararData = e => {
    setValorData(dataMask(e.target.value));
  }

  const mascararTelefone = e => {
    setValorTelefone(telefoneMask(e.target.value));
  }

    useEffect(() => {
      
      if (Object.keys(dados).length !== 0) {
        setValorCpf(dados.cpf);
        setNome(dados.nome);
        setValorData(reformatarData(dados.dataNascimento));
        setValorTelefone(dados.numeroTelefone);
        dados.registroFiscal && setRegistro(dados.registroFiscal);
        dados.cnh && setCnh(dados.cnh);
      }
    }, []);

 useEffect(() => {
   adicionarDados({
     nome,
     cpf: valorCpf,
     dataNascimento: formatarData(valorData),
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

      {dados.registroFiscal && (
        <InputComRotulo
          value={registro}
          onChange={(e) => setRegistro(e.target.value)}
          texto="Registro Fiscal"
          maxLength="20"
          required
        />
      )}

      {dados.cnh && (
        <InputComRotulo
          texto="CNH"
          maxLength="11"
          required
          value={cnh}
          onChange={(e) => setCnh(e.target.value)}
        />
      )}

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
