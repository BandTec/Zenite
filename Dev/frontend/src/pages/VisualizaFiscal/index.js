import React from 'react';

import { Container, Row, BotaoRelatorio ,BotaoNovoFiscal, Tela, Acoes, Cabecalho } from './styles';
import MenuLateral from '../../components/MenuLateral'
import BotaoClaro from '../../components/BotaoClaro'
import BotaoEscuro from '../../components/BotaoEscuro'

import Titulo from '../../components/Titulo'

export default function CadastroFiscal() {
  return (
    <Container>
        <MenuLateral />
        <Tela>
          <Row>
            <Cabecalho>
              <Titulo textoMenor='consulta do fiscal' textoMaior=''/>
            </Cabecalho>
          </Row>
          <Row>
            <Acoes>
              <BotaoNovoFiscal>
                <BotaoEscuro descricao='Novo Fiscal'/>
              </BotaoNovoFiscal>
              <BotaoRelatorio>
                <BotaoClaro descricao='relatório'/>
              </BotaoRelatorio>
            </Acoes>
          </Row>
        </Tela>
    </Container>
  );
}
