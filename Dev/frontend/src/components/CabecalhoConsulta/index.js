import React from 'react';

import { Row } from './styles';
import Botao from "../Botao";
import Titulo from "../Titulo";
import InputFile from "../InputFile";

function CabecalhoConsulta({ titulo, botaoTitulo, url, totalItens, onClick, importarFile, importarOnClick, importarTitle, exportarTitle, exportarOnclick }) {
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

        {importarFile && (
          <InputFile
            onChange={importarFile}  
          />
        )}

        {importarOnClick && (
          <Botao
            descricao={importarTitle}
            estiloEscuro={true}
            onClick={importarOnClick}
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