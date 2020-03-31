import React from 'react';

 import { Container, Tela, Row, Cabecalho, BotaoEditarFiscal, CorpoRelatorio, CaixaDados, CaixaTabela } from './styles';
 import MenuLateral from './../../components/MenuLateral';
 import Titulo from  '../../components/Titulo';
 import TituloTipoDado from '../../components/TituloTipoDado';
 import TituloDado from '../../components/TituloDado';
 import Botao from '../../components/Botao';
 import Tabela from '../../components/Tabela';

export default function DetalhesFiscal() {

  function criaDados(name, dados1){
    return {name, dados1}
  }

  const dadosCabecalho = [
    criaDados('Código Linha', 'Destino')
  ];

  const dadosCorpo = [
    criaDados('917H-10', 'Vila Mariana'),
    criaDados('8001-10', 'Vila Piaui'),
    criaDados('8002-10', 'Pirituba'),
    criaDados('8004-10', 'Santa'),
  ];

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

            <Row>
             
              <CorpoRelatorio>
                
                <CaixaDados>
                  <TituloTipoDado texto="Dados Pessoais"/>
                 <TituloDado tipo='Registro Fiscal' descricao='00123245553'/> 
                   <TituloDado tipo="Nome" descricao="Raissa Arantes"/> 
                  <TituloDado tipo="Data de Nascimento" descricao="28/06/1997"/> 
                  <TituloDado tipo="Telefone" descricao="11 940028922"/> 
                  <TituloDado tipo="Supervisor" descricao="Alex Barreira"/> 
                  <TituloDado tipo="Email" descricao="raissa.domingos@bandtec.com.br"/>  

                </CaixaDados>

                <CaixaDados>
                   <TituloTipoDado texto="Endereço"/>
                  
                  <TituloDado tipo="Cep" descricao="01551-090"/> 
                  <TituloDado tipo="Logradouro" descricao="Rua Antonieta de Moraes"/> 
                  <TituloDado tipo="Numero" descricao="130B"/> 
                  <TituloDado tipo="Complemento" descricao="APT 21"/> 
                  <TituloDado tipo="Cidade" descricao="São Paulo"/> 
                  <TituloDado tipo="Estado" descricao="SP"/> 

                </CaixaDados>

                <CaixaDados>
                  <TituloTipoDado texto="Linhas Gerenciadas"/>

                  <CaixaTabela>
                    <Tabela tabela={1} dadosCabecalho={dadosCabecalho} dadosCorpo={dadosCorpo}/>
                  </CaixaTabela>
                  

                </CaixaDados>  
              </CorpoRelatorio>
              
            </Row>
        </Tela>
    </Container>
  );
}
