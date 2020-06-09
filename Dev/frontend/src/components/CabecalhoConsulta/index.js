import React from 'react';

import { Row } from './styles';
import Botao from "../Botao";
import Titulo from "../Titulo";

function CabecalhoConsulta({ titulo, botaoTitulo, url, totalItens, onClick }) {
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

        <Botao descricao="relatório" url={`/${url}`} />
      </div>
    </Row>
  );
}

export default CabecalhoConsulta;