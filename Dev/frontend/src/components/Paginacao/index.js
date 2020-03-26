import React from 'react';

import { Container, Row, Acoes, BotaoVoltar, BotaoProximo, TextoPaginacaoDiv} from './styles';
import TextoPaginacao from './TextoPaginacao';
import Botao from '../Botao';


export default function Paginacao() {
  return (
    <Container>
        <Row>
          <Acoes>
                <BotaoVoltar>
                    <Botao descricao='Voltar' estiloEscuro={false}/>
                </BotaoVoltar>
            
                <TextoPaginacaoDiv>
                    <TextoPaginacao paginaAtual='1' totalPaginas='10'/>
                </TextoPaginacaoDiv>

                <BotaoProximo>
                    <Botao descricao='Próximo' estiloEscuro={false}/>
                </BotaoProximo>
            </Acoes>
        </Row>
    </Container>
  );
}