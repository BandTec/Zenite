import React, { useState, useEffect } from "react";

import { Container, Row, Cabecalho, CorpoRelatorio, CaixaDados, CaixaTabela } from './styles';
import Titulo from  '../../components/Titulo';
import TituloTipoDado from '../../components/TituloTipoDado';
import TituloDado from '../../components/TituloDado';
import Botao from '../../components/Botao';
import Tabela from "../../components/Tabela2";
import api from "../../services/api";

export default function DetalhesFiscal(props) {
    const id = props.match.params.id;
  const [dados, setDados] = useState({});
  const [corpo, setCorpo] = useState([]);

  useEffect(() => {
    async function consultar() {
      const token = localStorage.getItem("token");

      const response = await api.get(`/api/fiscal/${id}`, {
        headers: { Authorization: token },
      });

      setDados(response.data);
      console.log(response.data);
    }

    async function consultarOnibus() {
      const token = localStorage.getItem("token");
      const response = await api.get(`/api/fiscal/${id}/linhas`, {
        headers: { Authorization: token },
      });

      let temp = [];
      let dados = response.data;
      dados.forEach((item) => {
        temp.push(criaDados(item.id, item.numero, item.pontoIda.nome, item.pontoVolta.nome));
      });
      setCorpo(temp);
    }

    consultar();
    consultarOnibus();
  }, []);

  function criaDados(id, numero, pontoIda, pontoVolta) {
    return { id, numero, pontoIda, pontoVolta };
  }


  return (
    <Container>
      <Row>
        <Cabecalho>
          <Titulo textoMenor="Detalhes" textoMaior="Fiscal" />
          <Botao
            descricao="Editar fiscal"
            estiloEscuro={true}
            url={`/fiscal/editar/${id}`}
          />
        </Cabecalho>
      </Row>

      <Row>
         { dados.conta && <CorpoRelatorio> 
          <CaixaDados>
            <TituloTipoDado texto="Dados Pessoais" />

            <TituloDado
              tipo="Registro Fiscal"
              descricao={dados.registroFiscal}
            />

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

          <CaixaDados>
            <TituloTipoDado texto="Linhas Gerenciadas" />

            <CaixaTabela>
              <Tabela tipo="fiscal" dados={corpo} temAcoes={false} />
              <p>{corpo.length === 0 ? "Fiscal não está associado a nenhuma linha." : ""}</p>
            </CaixaTabela>
          </CaixaDados>
        </CorpoRelatorio>
}
      </Row>
    </Container>
  );
}
