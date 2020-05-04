import React, {useState, useEffect} from 'react';
import { Link } from 'react-router-dom';
import api from "../../services/api";

import { Container, CorpoPagina, FormContainer, Titulo, Subtitulo, Caixa } from './styles';
import BotaoForm from '../../components/BotaoForm';
import InputComRotulo from '../../components/InputComRotulo';


export default function CadastroOnibus(props) {
  const [numero, setNumero] = useState("");
  const [codigo, setCodigo] = useState("");
  const [idDispositivo, setIdDispositivo] = useState("");

  const caminho = props.match.path;
  const id = props.match.params.id;
  const isEdicao = caminho.includes("editar");
  const tipoPagina = isEdicao ? "Edição" : "Cadastro";
  
  const cadastro = async () => {
    let corpo = {
      "numero": numero,
      "dispositivo": {
        "codigo": codigo,
        "tipoDispositivo": {
        "id": 2
        }
      } 
    };

    try {
      const token = await localStorage.getItem("token");

      const response = await api.post("/api/onibus", corpo, {
        headers: { Authorization: token },
      });

      if (response.status === 201) {
        props.history.push("/onibus");
      } 
    } catch (e) {
      alert("Ocorreu um erro. Tente de novo.");
    }
  }

  useEffect(() => {
    async function consultarEdicao() {
      try {
        const token = localStorage.getItem("token");

        const response = await api.get(`/api/onibus/${id}`, {
          headers: { Authorization: token },
        });

        const dados = response.data;

        setNumero(dados.numero);
        setCodigo(dados.dispositivo.codigo);
        setIdDispositivo(dados.dispositivo.id);
      } catch (e) {
        alert("Ocorreu um erro. Tente de novo.");
      }
    }

    consultarEdicao();
  }, [id]);

  const editar= async () => {
    let corpo = {
      "id": id,
      "numero": numero,
      "dispositivo": {
        "id": idDispositivo,
        "codigo": codigo,
        "tipoDispositivo": {
        "id": 2
        }
      } 
    };
    console.log(corpo);
    try {
      const token = await localStorage.getItem("token");

      const response = await api.put("/api/onibus", corpo, {
        headers: { Authorization: token },
      });

      if (response.status === 200) {
        props.history.push("/onibus");
      } 
    } catch (e) {
      alert("Ocorreu um erro. Tente de novo.");
    }
  }

   const concluir = () => {
      isEdicao ? editar() : cadastro();
    };
  
  return (
    <Container>
      <CorpoPagina>
        <FormContainer>
          <Link to="/onibus">
            <BotaoForm texto="VOLTAR" ladoDireito={false} />
          </Link>

          <Caixa>
            <Subtitulo>{tipoPagina} DO ÔNIBUS</Subtitulo>
            <Titulo>Dados</Titulo>

            <InputComRotulo
              texto="NÚMERO DO ÔNIBUS"
              maxLength="10"
              name="numeroOnibus"
              type="text"
              value={numero}
              onChange={(e) => setNumero(e.target.value)}
              required
            />

            <InputComRotulo
              texto="Código do Dispositivo"
              maxLength="20"
              name="codigoDispositivo"
              value={codigo}
              onChange={(e) => setCodigo(e.target.value)}
              required
            />
          </Caixa>
          
          <BotaoForm texto="Finalizar" concluir={true} criarJson={concluir} />

        </FormContainer>
      </CorpoPagina>
    </Container>
  );
}
