import React from 'react';

import { Container, Row, BotaoRelatorio ,BotaoNovoFiscal, Tela, Acoes, Cabecalho } from './styles';
import MenuLateral from '../../components/MenuLateral';
import Botao from '../../components/Botao';

import Titulo from '../../components/Titulo';
import Paginacao from '../../components/Paginacao';

export default function CadastroFiscal() {
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
                <Botao descricao='Novo Fiscal' estiloEscuro={true}/>
              </BotaoNovoFiscal>

              <BotaoRelatorio>
                <Botao descricao='relatório'/>
              </BotaoRelatorio>

            </Acoes>
          </Row>
          <Row>
            <Paginacao/>
          </Row>
        </Tela>
    </Container>
  );
}
