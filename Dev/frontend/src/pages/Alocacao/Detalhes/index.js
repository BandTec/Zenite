import React from "react";

import {
  Row,
  Col,
  Rotulo,
  InfoBlock,
  InfoTitle,
  InfoDetalhe,
} from "./styles";

export default function Detalhes({tipo, objeto}) {

  return (
    <InfoBlock>
      {tipo === "Fiscal" ? (
        <>
          <InfoTitle>{objeto.dados.nome}</InfoTitle>
          <Rotulo>Alocado com:</Rotulo>
          {objeto.dados.linhas.map((item) => (
            <InfoDetalhe key={item.numero}>
              {item.numero} - {item.pontoIda.nome} /{item.pontoVolta.nome}
            </InfoDetalhe>
          ))}
        </>
      ) : tipo === "Motorista" ? (
        <>
          <InfoTitle>{objeto.dados.nome}</InfoTitle>
          <Rotulo>Alocado com:</Rotulo>
          <InfoDetalhe></InfoDetalhe>
        </>
      ) : tipo === "Ônibus" ? (
        <>
          <InfoTitle>{objeto.dados.numero}</InfoTitle>
          <Col>
            <InfoDetalhe>{objeto.dados.placa}</InfoDetalhe>
            <InfoDetalhe>{objeto.dados.modelo}</InfoDetalhe>
          </Col>
        </>
      ) : (
        <>
          <InfoTitle>{objeto.dados.numero}</InfoTitle>
          <Row start={true}>
            <Col start={true}>
              <Rotulo>Ponto Inicial:</Rotulo>
              <InfoDetalhe>{objeto.dados.pontoIda.nome}</InfoDetalhe>
            </Col>

            <Col>
              <Rotulo>Ponto Final:</Rotulo>
              <InfoDetalhe>{objeto.dados.pontoVolta.nome}</InfoDetalhe>
            </Col>
          </Row>
        </>
      )}
    </InfoBlock>
  );
}
