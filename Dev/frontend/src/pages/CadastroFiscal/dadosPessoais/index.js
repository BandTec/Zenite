import React, {useState} from 'react';

import { Container, CaixaHorizontal, CorpoPagina, FormContainer, Titulo, Subtitulo, Caixa } from './styles';
import BotaoForm from './../../../components/BotaoForm';
import StatusPage from './../../../components/StatusPage';
import MenuLateral from './../../../components/MenuLateral';
import InputComRotulo from './../../../components/InputComRotulo';

import { cpfMask, telefoneMask, dataMask } from "./../../../functions/Mascaras/mask";

export default function CadastroFiscal(props) {

  const [valorCpf, setValorCpf] = useState("");
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

  const caminho = props.match.path;
  const {id} = props.match.params;
  const tipoPagina = caminho.includes("editar") ? "Edição" : "Cadastro";
  const tipoUrl = caminho.includes("editar") ? "editar" : "cadastro";

  return (
    <Container>
      <MenuLateral itemAtivo="fiscal" />

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

            <InputComRotulo texto="Nome" name="nome" maxLength="100" required />

            <InputComRotulo
              texto="CPF"
              maxLength="14"
              name="cpf"
              value={valorCpf}
              onChange={mascararCpf}
              required
            />

            <InputComRotulo texto="Registro Fiscal" maxLength="20" required />

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

          <BotaoForm texto="Próximo" url={`/fiscal/${tipoUrl}/2/${id}`} />
        </FormContainer>
      </CorpoPagina>
    </Container>
  );
}
