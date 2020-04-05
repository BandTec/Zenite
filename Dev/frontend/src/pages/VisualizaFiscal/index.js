import React from 'react';

import { Container, Row, BotaoRelatorio ,BotaoNovoFiscal, Tela, Acoes, Cabecalho, CaixaTabela } from './styles';
import MenuLateral from '../../components/MenuLateral';
import Botao from '../../components/Botao';
import Tabela from '../../components/Tabela';
import Titulo from '../../components/Titulo';
import Paginacao from '../../components/Paginacao';

export default function CadastroFiscal() {

  function criaDados(registro, nome, dataNasc, telefone, supervisor, acoes){
    return {registro, nome, dataNasc, telefone, supervisor, acoes}
  }

  const dadosCabecalho = [
    criaDados('Registro Fiscal', 'Nome', 'Data Nasc.', 'Telefone', 'Supervisor', 'Ações')
  ];

  const dadosCorpo = [
    criaDados('00123245553', 'Vitor Leonardo', '17/02/1987', '(11) 98660-9314', 'Diego Moreno', 'icones'),
    criaDados('00236751234', 'Fernanda Esteves', '21/11/1993', '(11) 98660-9314', 'Rafael Fagundes', 'icones'),
    criaDados('0012345553', 'Lais da Silva', '21/02/1995', '(11) 98660-9314', 'Marise Miranda', 'icones'),
    criaDados('0012345553', 'João Pedro', '05/08/1996', '(11) 98660-9314', 'Marise Miranda', 'icones'),
    criaDados('0012345553', 'Aline Ayla', '30/06/1992', '(11) 98660-9314', 'Diego Moreno', 'icones'),
    criaDados('0012345553', 'Lucas Mariano', '13/04/1995', '(11) 98660-9314', 'Rafael Fagundes', 'icones'),
    // criaDados('0012345553', 'Rafael Estevan', '10/10/1988', '(11) 98660-9314', 'Rafael Fagundes', 'icones'),
    // criaDados('0012345553', 'Carlos Olivera', '02/12/1993', '(11) 98660-9314', 'Marise Miranda', 'icones'),
  ];

  return (
    <Container>
        <MenuLateral itemAtivo="fiscal" />
        <Tela>
          <Row>
            <Cabecalho>
              <Titulo textoMenor='consulta do fiscal' textoMaior=''/>
            </Cabecalho>
          </Row>
          <Row>
            <Acoes>

              <BotaoNovoFiscal>
                <Botao 
                  descricao='Novo Fiscal' 
                  estiloEscuro={true}
                  url="/fiscal/cadastro/1"
                />
              </BotaoNovoFiscal>

              <BotaoRelatorio>
                <Botao descricao='relatório'/>
              </BotaoRelatorio>

            </Acoes>
          </Row>

          <Row>
            <CaixaTabela>
              <Tabela Tabela={2} dadosCabecalho={dadosCabecalho} dadosCorpo={dadosCorpo} />
            </CaixaTabela>
          </Row>

          <Row>
            <Paginacao/>
          </Row>
        </Tela>
    </Container>
  );
}
