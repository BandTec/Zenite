import React, {useState} from 'react';

import { Container, CaixaHorizontal, CorpoPagina, FormContainer, Titulo, Subtitulo, Caixa } from './styles';
import BotaoForm from './../../../components/BotaoForm';
import StatusPage from './../../../components/StatusPage';

import InputComRotulo from './../../../components/InputComRotulo';

import { cpfMask, telefoneMask, dataMask } from "./../../../functions/Mascaras/mask";

export default function DadosPessoais({ pagina, setPagina, tipoUrl, tipoPagina }) {
  
  const [nome, setNome ] = useState("");
  const [valorCpf, setValorCpf] = useState("");
  const [registro, setRegistro] = useState("");
  const [valorData, setValorData] = useState("");
  const [valorTelefone, setValorTelefone] = useState("");

  const mascararCpf = (e) => {
    setValorCpf(cpfMask(e.target.value));
  }

  const mascararData = (e) => {
    setValorData(dataMask(e.target.value));
  }

  const mascararTelefone = (e) => {
    setValorTelefone(telefoneMask(e.target.value));
  }



  return (
    <Container>
      
      <CorpoPagina>
        <CaixaHorizontal center={true}>
          <StatusPage
            ativo={true}
            texto="Dados Pessoais"
            temProximoPasso={true}
          />

          <StatusPage ativo={false} texto="Endereço" temProximoPasso={true} />

          <StatusPage
            ativo={false}
            texto="Dados de Acesso"
            temProximoPasso={false}
          />
        </CaixaHorizontal>

        <FormContainer>
          <BotaoForm texto="VOLTAR" url="/fiscal" ladoDireito={false} />

          <Caixa>
            <Subtitulo>{tipoPagina} DO FISCAL</Subtitulo>
            <Titulo>DADOS CADASTRAIS</Titulo>

            <InputComRotulo value={nome} onChange={e => setNome(e.target.value)} texto="Nome" name="nome" maxLength="100" required />

            <InputComRotulo
              texto="CPF"
              maxLength="14"
              name="cpf"
              value={valorCpf}
              onChange={mascararCpf}
              required
            />

            <InputComRotulo value={registro} onChange={e => setRegistro(e.target.value)} texto="Registro Fiscal" maxLength="20" required />

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
          </Caixa>

          <BotaoForm texto="Próximo" url={`/fiscal/${tipoUrl}/${pagina}`}/>
        </FormContainer>
      </CorpoPagina>
    </Container>
  );
}
