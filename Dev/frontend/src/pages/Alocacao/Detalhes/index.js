import React from "react";
import Swal from "sweetalert2";
import {
  Row,
  Col,
  Rotulo,
  InfoBlock,
  InfoTitle,
  InfoDetalhe,
  Img,
  RowBetween,
} from "./styles";
import ExcluirIcon from "./../../../assets/icons/excluir.svg";
import api from "./../../../services/api";

export default function  Detalhes({tipo, objeto}) {

 const excluir = async (idFiscal, idLinha) => {
    let continuar = await Swal.fire({
      title: "Deseja realmente excluir estes dados?",

      showCancelButton: true,
      cancelButtonText: "Cancelar",
      confirmButtonText: "Excluir",
    });
     
   if (continuar.value) {
     try {
     const token = localStorage.getItem("token");
     
     const response = await api.delete(`/api/fiscal/${idFiscal}/linhas/${idLinha}`, {
       headers: { Authorization: token },
     });

     if (response.status === 200) {
       Swal.fire("Sucesso!", "Dado excluído com sucesso.", "success");
       window.location.reload(); 
     }else {
       Swal.fire("Erro!", "Tente novamente.", "error");
     }
    } catch (e){
      Swal.fire("Erro!", "Tente novamente.", "error");
    }
   }
 };

  return (
    <InfoBlock>
      {tipo === "Fiscal" && objeto.dados.linhas ? (
        <>
          <InfoTitle>{objeto.dados.nome}</InfoTitle>
          <Rotulo>Alocado com:</Rotulo>
          {objeto.dados.linhas.map((item) => (
            <RowBetween key={item.numero}>
              <InfoDetalhe>
                {item.numero} - {item.pontoIda.nome} /{item.pontoVolta.nome}
              </InfoDetalhe>
              <button onClick={() => excluir(objeto.dados.id, item.id)}>
                <Img src={ExcluirIcon} title="Excluir dado" />
              </button>
            </RowBetween>
          ))}
        </>
      ) : tipo === "Motorista" && objeto.dados.carros ? (
        <>
          <InfoTitle>{objeto.dados.nome}</InfoTitle>
          <Rotulo>Alocado com: {objeto.dados.carros.length == 0 ? "Nenhum ônibus" : ""}</Rotulo>
          {objeto.dados.carros.map((item) => (
            <Col>
              <Rotulo>Ônibus:</Rotulo>
              <InfoDetalhe>Placa: {item.placa}</InfoDetalhe>
              <InfoDetalhe>Modelo: {item.modelo}</InfoDetalhe>
              <InfoDetalhe>Número: {item.numero}</InfoDetalhe>
            </Col>
          ))}
        </>
      ) : tipo === "Ônibus" && objeto.dados.numero ? (
        <>
          <InfoTitle>{objeto.dados.numero}</InfoTitle>

          <Col>
            <InfoDetalhe>Placa: {objeto.dados.placa}</InfoDetalhe>
            <InfoDetalhe>Modelo: {objeto.dados.modelo}</InfoDetalhe>
            <InfoDetalhe>Número: {objeto.dados.numero}</InfoDetalhe>
            <InfoDetalhe>
              Atrelado a uma Linha:{" "}
              {objeto.dados.linhasId.length > 0 ? "Sim" : "Não"}
            </InfoDetalhe>
          </Col>
        </>
      ) : objeto.dados.pontoIda ? (
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
      ) : (
        <> </>
      )}
    </InfoBlock>
  );
}
