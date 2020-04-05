import React, {useState} from 'react';

import { Container, CaixaHorizontal, CorpoPagina, FormContainer, Titulo, Subtitulo, Caixa } 
from './styles';
import BotaoForm from '../../../components/BotaoForm';
import StatusPage from '../../../components/StatusPage';
import MenuLateral from '../../../components/MenuLateral';
import InputComRotulo from '../../../components/InputComRotulo';

import { cpfMask, telefoneMask, dataMask } from "../../../functions/Mascaras/mask";

export default function CadastroGerente() {

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

  return (
    <Container>
      <MenuLateral item="gerente" />

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
          <BotaoForm texto="VOLTAR" url="/gerente" ladoDireito={false} />

          <Caixa>
            <Subtitulo>CADASTRO DO GERENTE</Subtitulo>
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

          <BotaoForm texto="Próximo" url="/gerente/cadastro/2" />
        </FormContainer>
      </CorpoPagina>
    </Container>
  );
}