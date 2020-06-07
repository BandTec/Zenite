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

export default function Detalhes({ tipo, objeto, atualizarDado }) {
                 const excluir = async (idFiscal, idLinha, index) => {
                   let continuar = await Swal.fire({
                     title: "Deseja realmente excluir estes?",

                     showCancelButton: true,
                     cancelButtonText: "Cancelar",
                     confirmButtonText: "Excluir",
                   });

                   if (continuar.value) {
                     try {
                       const token = localStorage.getItem("token");

                       const response = await api.delete(
                         `/api/fiscal/${idFiscal}/linhas/${idLinha}`,
                         {
                           headers: { Authorization: token },
                         }
                       );

                       if (response.status === 200) {
                         Swal.fire(
                           "Sucesso!",
                           "Dado excluído com sucesso.",
                           "success"
                         );
                         atualizarDado(index);
                       } else {
                         Swal.fire("Erro!", "Tente novamente.", "error");
                       }
                     } catch (e) {
                       console.log(e);
                       Swal.fire("Erro!", "Tente novamente.", "error");
                     }
                   }
                 };

                 return (
                   <InfoBlock>
                     {tipo === "Fiscal" && objeto.linhas ? (
                       <>
                         <InfoTitle>{objeto.nome}</InfoTitle>
                         <InfoDetalhe>
                           <Rotulo>Alocado com: </Rotulo>
                           {objeto.linhas.length === 0 && "Nenhuma Linha"}
                         </InfoDetalhe>
                         {objeto.linhas.map((item, index) => (
                           <RowBetween key={item.numero}>
                             <InfoDetalhe>
                               {item.numero} - {item.pontoIda.nome} /
                               {item.pontoVolta.nome}
                             </InfoDetalhe>
                             <button
                               onClick={() =>
                                 excluir(objeto.id, item.id, index)
                               }
                             >
                               <Img src={ExcluirIcon} title="Excluir dado" />
                             </button>
                           </RowBetween>
                         ))}
                       </>
                     ) : tipo === "Motorista" && objeto.nome ? (
                       <>
                         <InfoTitle>{objeto.nome}</InfoTitle>

                         <Rotulo>
                           Alocado com:{" "}
                           {objeto.carro === null && "Nenhum ônibus"}
                         </Rotulo>

                         {objeto.carro && (
                           <Col>
                             <Rotulo>Ônibus:</Rotulo>
                             <InfoDetalhe>
                               <Rotulo>Número: </Rotulo>
                               {objeto.carro.numero}
                             </InfoDetalhe>
                             <InfoDetalhe>
                               <Rotulo>Placa: </Rotulo>
                               {objeto.carro.placa}
                             </InfoDetalhe>
                             <InfoDetalhe>
                               <Rotulo>Modelo: </Rotulo>
                               {objeto.carro.modelo}
                             </InfoDetalhe>
                           </Col>
                         )}
                       </>
                     ) : tipo === "Ônibus" && objeto.numero ? (
                       <>
                         <InfoTitle>{objeto.numero}</InfoTitle>

                         <Col>
                           <InfoDetalhe>
                             <Rotulo>Número: </Rotulo>
                             {objeto.numero}
                           </InfoDetalhe>
                           <InfoDetalhe>
                             <Rotulo>Placa: </Rotulo>
                             {objeto.placa}
                           </InfoDetalhe>
                           <InfoDetalhe>
                             <Rotulo>Modelo: </Rotulo>
                             {objeto.modelo}
                           </InfoDetalhe>
                         </Col>
                         <InfoDetalhe>
                           <Rotulo>Motorista: </Rotulo>
                           {objeto.motorista || "Sem Motorista"}
                         </InfoDetalhe>
                         <InfoDetalhe>
                           <Rotulo>Atrelado a uma Linha:</Rotulo>{" "}
                           {!objeto.linha && "Não"}
                         </InfoDetalhe>
                         {objeto.linha && (
                           <InfoDetalhe>{objeto.linha}</InfoDetalhe>
                         )}
                       </>
                     ) : objeto.pontoIda ? (
                       <>
                         <InfoTitle>{objeto.numero}</InfoTitle>
                         <InfoDetalhe>
                           <Rotulo>Fiscal: </Rotulo>
                           {objeto.fiscal || "Sem fiscal"}
                         </InfoDetalhe>

                         <Row start={true}>
                           <Col start={true}>
                             <Rotulo>Ponto Inicial:</Rotulo>
                             <InfoDetalhe>{objeto.pontoIda.nome}</InfoDetalhe>
                           </Col>

                           <Col>
                             <Rotulo>Ponto Final:</Rotulo>
                             <InfoDetalhe>{objeto.pontoVolta.nome}</InfoDetalhe>
                           </Col>
                         </Row>
                         <Rotulo>
                           Quantidade de Ônibus: {objeto.carros.length}
                         </Rotulo>

                         {objeto.carros.map((item) => (
                           <InfoDetalhe>{item}</InfoDetalhe>
                         ))}
                       </>
                     ) : (
                       <> </>
                     )}
                   </InfoBlock>
                 );
               }
