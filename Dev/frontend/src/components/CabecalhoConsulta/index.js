import React from 'react';

import { Row } from './styles';
import Botao from "../Botao";
import Titulo from "../Titulo";

function CabecalhoConsulta({ titulo, botaoTitulo, url, totalItens, onClick, exportarTitle, exportarOnclick }) {
  return (
    <Row>
      <Titulo
        textoMenor="consulta"
        textoMaior={titulo}
        totalItens={totalItens}
      />

      <div>
        {url && (
          <Botao
            descricao={botaoTitulo}
            estiloEscuro={true}
            url={`/${url}/cadastro`}
          />
        )}

        {onClick && (
          <Botao
            descricao={botaoTitulo}
            estiloEscuro={true}
            onClick={onClick}
          />
        )}

         {exportarOnclick && (
          <Botao
            descricao={exportarTitle}
            estiloEscuro={true}
            onClick={exportarOnclick}
          />
        )}

        <Botao descricao="relatório" url={`/${url}`} />
      </div>
    </Row>
  );
}

export default CabecalhoConsulta;