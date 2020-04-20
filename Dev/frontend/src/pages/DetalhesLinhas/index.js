import React, {useState, useEffect} from 'react';

import { Container, Row, Cabecalho, CaixaDados, CaixaTabela } from './styles';
import Titulo from  '../../components/Titulo';
import TituloTipoDado from '../../components/TituloTipoDado';
import TituloDado from '../../components/TituloDado';
import Botao from '../../components/Botao';
import Tabela from '../../components/Tabela2';
import api from "../../services/api";


export default function DetalhesLinha(props) {
   const id = props.match.params.id;
   const [dados, setDados] = useState({});
   const [corpo, setCorpo] = useState([]);

  useEffect(() => {
    async function consultar() {
      const token = localStorage.getItem("token");

      const response = await api.get(`/api/linha/${id}`, {
        headers: { Authorization: token },
      });

      setDados(response.data);
    }

      async function consultarOnibus() {
        const token = localStorage.getItem("token");
        const response = await api.get(`/api/linha/${id}/onibus`, {
          headers: { Authorization: token },
        });

       let temp = [];
       let dados = response.data;
       dados.forEach((item) => {
         temp.push(criaDados(item.id, item.numero, item.dispositivo.codigo));
       });
       setCorpo(temp);
      }

    consultar();
    consultarOnibus(); 
  }, []);

    function criaDados(id, numero, dispositivo) {
      return { id, numero, dispositivo };
    }

  return (
    <Container>
      <Cabecalho>
        <Titulo textoMenor="Detalhes" textoMaior="Linha" />
        <Botao
          descricao="Editar Linha"
          estiloEscuro={true}
          url={`/linha/editar/${id}`}
        />
      </Cabecalho>

      <Row>
        <CaixaDados>
          <TituloTipoDado texto="Dados" />
          {dados.numero && (
            <>
              <TituloDado tipo="Número da Linha" descricao={dados.numero} />
              <TituloDado tipo="Parada Ida" descricao={dados.pontoIda.nome} />
              <TituloDado
                tipo="Parada Volta"
                descricao={dados.pontoVolta.nome}
              />
            </>
          )}
        </CaixaDados>

        <CaixaDados>
          <TituloTipoDado texto="Ônibus da linha" />

          <CaixaTabela>
            <Tabela tipo="linha" dados={corpo} temAcoes={false} />
            <p>
              {corpo.length === 0
                ? "Linha não está associada a nenhum ônibus."
                : ""}
            </p>
          </CaixaTabela>
        </CaixaDados>
      </Row>
    </Container>
  );
}
