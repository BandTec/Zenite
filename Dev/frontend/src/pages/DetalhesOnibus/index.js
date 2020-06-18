/* eslint react-hooks/exhaustive-deps: 0 */
import React, { useState, useEffect } from "react";

import {
  Container,
  Row,
  Cabecalho,
  CorpoRelatorio,
  CaixaDados,
} from "./styles";
import Titulo from "../../components/Titulo";
import TituloTipoDado from "../../components/TituloTipoDado";
import TituloDado from "../../components/TituloDado";
import Botao from "../../components/Botao";
import api from "../../services/api";
import Loader from "../../components/Loader";

export default function DetalhesOnibus(props) {
  const id = props.match.params.id;
  const [dados, setDados] = useState({});

  useEffect(() => {
    async function consultar() {
      const token = localStorage.getItem("token");
        
      const response = await api.get(`/api/onibus/${id}`, {
        headers: { Authorization: token },
      });
      console.log(response.data);
      setDados(response.data);
    }

    consultar();
    console.log(dados);
  }, [id]);

  return !dados.acessivel ? (
    <Loader />
  ) : (
    <Container>
      <Row>
        <Cabecalho>
          <Titulo textoMenor="Detalhes" textoMaior="Ônibus" />
          <Botao
            descricao="Editar ônibus"
            estiloEscuro={true}
            url={`/onibus/editar/${id}`}
          />
        </Cabecalho>
      </Row>

      <Row>
        {dados.acessivel && (
          <CorpoRelatorio>
            <CaixaDados>
              <TituloTipoDado texto="Dados Veículo" />
              <TituloDado tipo="Número" descricao={dados.numero} />
              <TituloDado tipo="Placa" descricao={dados.placa} />
              <TituloDado tipo="Modelo" descricao={dados.modelo} />
              <TituloDado
                tipo="acessível"
                descricao={dados.acessivel ? "Sim" : "Não"}
              />
              <TituloDado
                tipo="Quantidade de Passageiros Sentados"
                descricao={dados.qtdPassageirosSentados}
              />
              <TituloDado
                tipo="Quantidade de Passageiros em Pé"
                descricao={dados.qtdPassageirosEmPe}
              />
              <TituloDado
                tipo="dispositivo"
                descricao={dados.dispositivo.codigo}
              />
            </CaixaDados>

            <CaixaDados>
              <TituloTipoDado texto="Gerente" />
              <TituloDado tipo="Nome" descricao={dados.gerente.nome} />
              <TituloDado tipo="CPF" descricao={dados.gerente.cpf} />
            </CaixaDados>

            <CaixaDados>
              <TituloTipoDado texto="Alocado" />
              <TituloDado tipo="Linha" descricao={dados.linha || "Sem linha"} />
              <TituloDado
                tipo="motorista"
                descricao={dados.motorista || "Sem motorista"}
              />
            </CaixaDados>
          </CorpoRelatorio>
        )}
      </Row>
    </Container>
  );
}
