import React from 'react';

import { Row } from './styles';
import Botao from "../Botao";
import Titulo from "../Titulo";

function CabecalhoConsulta({ titulo, botaoTitulo, url, totalItens, onClick, importarTitle, importarOnclick, exportarTitle, exportarOnclick }) {
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

        {importarOnclick && (
          <Botao
            descricao={importarTitle}
            estiloEscuro={true}
            onClick={importarOnclick}
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