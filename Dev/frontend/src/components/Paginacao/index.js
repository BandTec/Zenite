import React from 'react';

import { Container } from './styles';
import TextoPaginacao from './TextoPaginacao';
import Botao from '../Botao';


export default function Paginacao({pgAtual, totalPg, totalItens, mudarPag}) {
  console.log(pgAtual)
  return (
    <Container>
      <Botao
        descricao="Primeira"
        estiloEscuro={false}
        onClick={() => mudarPag(0)}
      />
      <Botao
        descricao="Voltar"
        estiloEscuro={false}
        onClick={() => mudarPag(pgAtual - 1)}
        disabled={pgAtual === 0}
      />
      <TextoPaginacao
        paginaAtual={pgAtual + 1}
        totalPaginas={totalPg}
        totalItens={totalItens}
      />
      <Botao
        descricao="Próximo"
        estiloEscuro={false}
        onClick={() => mudarPag(pgAtual + 1)}
        disabled={(pgAtual + 1) === totalPg}
      />
      <Botao
        descricao="Última"
        estiloEscuro={false}
        onClick={() => mudarPag(totalPg - 1)}
      />
    </Container>
  );
}