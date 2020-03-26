import React from 'react';

import { Container, CaixaHorizontal, CorpoPagina, FormContainer, Titulo, Subtitulo, Caixa } from './styles';
import BotaoForm from '../../components/BotaoForm';
import StatusPage from '../../components/StatusPage';
import MenuLateral from '../../components/MenuLateral';
import InputComRotulo from '../../components/InputComRotulo';

export default function CadastroFiscal() {
  return (
   <Container>
        <MenuLateral />

        <CorpoPagina>
          <CaixaHorizontal center={true}>
              <StatusPage ativo={false} texto="Dados Pessoais" temProximoPasso={true} />
            
              <StatusPage ativo={true} texto="Endereço" temProximoPasso={true} />
            
              <StatusPage ativo={false} texto="Dados de Acesso" temProximoPasso={false} />
          </CaixaHorizontal>

          <FormContainer>
            <BotaoForm texto="Voltar" ladoDireito={false} />
          
            <Caixa>
              
              <Subtitulo>CADASTRO DO FISCAL</Subtitulo>
              <Titulo>DADOS CADASTRAIS</Titulo>  

              <InputComRotulo texto="Nome" />

              <InputComRotulo texto="CPF" />

              <InputComRotulo texto="Registro Fiscal" />

              <CaixaHorizontal>
                <InputComRotulo texto="Data de Nascimento" pequeno={true}/>

              <InputComRotulo texto="Telefone" pequeno={true} />
              </CaixaHorizontal>
            </Caixa>

            <BotaoForm texto="Próximo"  />
          </FormContainer>
      </CorpoPagina>

     </Container>
  );
}
