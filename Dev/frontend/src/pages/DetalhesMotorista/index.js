import React, { useState, useEffect } from "react";

import { Container, Row, Cabecalho, CorpoRelatorio, CaixaDados } from './styles';
import Titulo from  '../../components/Titulo';
import TituloTipoDado from '../../components/TituloTipoDado';
import TituloDado from '../../components/TituloDado';
import Botao from '../../components/Botao';
import api from "../../services/api";

export default function DetalhesMotorista(props) {
    const id = props.match.params.id;
    const [dados, setDados] = useState({});

    useEffect(() => {
      async function consultar() {
        const token = localStorage.getItem("token");

        const response = await api.get(`/api/motorista/${id}`, {
          headers: { Authorization: token },
        });

        setDados(response.data);
      }

      consultar();
    }, []);

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
         { dados.conta && <CorpoRelatorio> 
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
            <TituloDado tipo="Logradouro" descricao={dados.endereco.logradouro} />
            <TituloDado tipo="Numero" descricao={dados.endereco.numero} />
            <TituloDado tipo="Complemento" descricao={dados.endereco.complemento} />
            <TituloDado tipo="Cidade" descricao={dados.endereco.cidade} />
            <TituloDado tipo="Estado" descricao={dados.endereco.estado} />
          </CaixaDados>

        </CorpoRelatorio>
}
      </Row>
    </Container>
  );
}
