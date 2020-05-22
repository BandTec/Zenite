import React, { useState, useEffect } from "react";

import {
  Container,
  Row,
  Cabecalho,
  CorpoRelatorio,
  CaixaDados,
  CaixaTabela,
} from "./styles";
import Titulo from "../../components/Titulo";
import TituloTipoDado from "../../components/TituloTipoDado";
import TituloDado from "../../components/TituloDado";
import Botao from "../../components/Botao";
import api from "../../services/api";

import Tabela from "../../components/Tabela2";

export default function DetalhesMotorista(props) {
  const id = props.match.params.id;
  const [dados, setDados] = useState({});
  const [corpo, setCorpo] = useState([]);
  useEffect(() => {
    async function consultar() {
      const token = localStorage.getItem("token");

      const response = await api.get(`/api/motorista/${id}`, {
        headers: { Authorization: token },
      });

      setDados(response.data);
    }

    async function consultarCarro() {
      const token = localStorage.getItem("token");
      const response = await api.get(`/api/motorista/${id}/onibus`, {
        headers: { Authorization: token },
      });

      let temp = [];
      let dados = response.data;
      dados.forEach((item) => {
        temp.push(
          criaDados(
            item.id,
            item.numero,
            item.placa,
            item.modelo
          )
        );
      });
      setCorpo(temp);
    }
    consultarCarro();
    consultar();
  }, [id]);

   function criaDados(id, numero, placa, modelo) {
     return { id, numero, placa, modelo };
   }


  return (
    <Container>
      <Row>
        <Cabecalho>
          <Titulo textoMenor="Detalhes" textoMaior="Motorista" />
          <Botao
            descricao="Editar Motorista"
            estiloEscuro={true}
            url={`/motorista/editar/${id}`}
          />
        </Cabecalho>
      </Row>

      <Row>
        {dados.conta && (
          <CorpoRelatorio>
            <CaixaDados>
              <TituloTipoDado texto="Dados Pessoais" />

              <TituloDado tipo="Nome" descricao={dados.nome} />

              <TituloDado
                tipo="Data de Nascimento"
                descricao={dados.dataNascimento}
              />

              <TituloDado tipo="Telefone" descricao={dados.numeroTelefone} />

              <TituloDado tipo="cpf" descricao={dados.cpf} />

              <TituloDado tipo="Email" descricao={dados.conta.email} />
            </CaixaDados>

            <CaixaDados>
              <TituloTipoDado texto="Endereço" />

              <TituloDado tipo="Cep" descricao={dados.endereco.cep} />
              <TituloDado
                tipo="Logradouro"
                descricao={dados.endereco.logradouro}
              />
              <TituloDado tipo="Numero" descricao={dados.endereco.numero} />
              <TituloDado
                tipo="Complemento"
                descricao={dados.endereco.complemento}
              />
              <TituloDado tipo="Cidade" descricao={dados.endereco.cidade} />
              <TituloDado tipo="Estado" descricao={dados.endereco.estado} />
            </CaixaDados>

            <CaixaDados>
              <TituloTipoDado texto="Ônibus Alocados" />

              <CaixaTabela>
                <Tabela tipo="onibus" dados={corpo} temAcoes={false} />
                <p>
                  {corpo.length === 0
                    ? "Motorista não está associado a nenhum ônibus."
                    : ""}
                </p>
              </CaixaTabela>
            </CaixaDados>
          </CorpoRelatorio>
        )}
      </Row>
    </Container>
  );
}