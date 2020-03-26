import React from 'react';

 import { Container, Tela, Row, Cabecalho, BotaoEditarFiscal, DadosPessoaisDiv } from './styles';
 import MenuLateral from './../../components/MenuLateral';
 import Titulo from  '../../components/Titulo';
 import Botao from '../../components/Botao';


export default function DetalhesFiscal() {
  return (
    <Container>
      <MenuLateral/>
        <Tela>
            <Row>
                <Cabecalho>
                    <Titulo textoMenor='Detalhes' textoMaior='Fiscal'/>               
                  
                    <BotaoEditarFiscal >
                      <Botao descricao='Editar Fiscal' estiloEscuro={true}/>
                    </BotaoEditarFiscal>
                
                </Cabecalho>
            </Row>
        </Tela>
    </Container>
  );
}
