import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import api from "../../services/api";
import Swal from 'sweetalert2';
import AsyncSelect from "react-select/async";

import {
  Container,
  CaixaHorizontal,
  CorpoPagina,
  FormContainer,
  Titulo,
  Subtitulo,
  Caixa,
  Select,
  Rotulo,
} from "./styles";

import BotaoForm from "../../components/BotaoForm";
import InputComRotulo from "../../components/InputComRotulo";

export default function CadastroLinha(props) {
  const caminho = props.match.path;
  const id = props.match.params.id;
  const isEdicao = caminho.includes("editar");
  const tipoPagina = isEdicao ? "Edição" : "Cadastro";

  const [linhaNumero, setLinhaNumero] = useState("");
  const [paradaIda, setParadaIda] = useState("");
  const [paradaVolta, setParadaVolta] = useState("");

  useEffect(() => {
    async function consultarEdicao() {
      const token = localStorage.getItem("token");
      try {
        const response = await api.get(`/api/linha/${id}`, {
          headers: { Authorization: token },
        });

        const dados = response.data;

        setParadaVolta(option(dados.pontoVolta));
        setParadaIda(option(dados.pontoIda));
        setLinhaNumero(dados.numero);
      } catch (e) {
        alert("Ocorreu um erro. Tente de novo.");
      }
    }

    id && consultarEdicao();
  }, [id]);

  const option = (e) => {
    return {
      value: e.id,
      label: e.nome,
      dados: e,
    };
  };

  const cadastrar = async () => {
    let dados = {
      numero: linhaNumero,
      pontoIda: { id: paradaIda.dados.id },
      pontoVolta: { id: paradaVolta.dados.id },
    };
    
    try {
      const token = await localStorage.getItem("token");
      const response = await api.post("/api/linha", dados, {
        headers: { Authorization: token },
      });

      if (response.status === 201) {
        props.history.push("/linha");
        Swal.fire({
          position: 'flex-end',
          icon: 'success',
          title: 'Cadastrado com Sucesso',
          showConfirmButton: false,
          timer: 2000
        });
      } else {
        alert("Ocorreu um erro. Tente de novo");
      }
   }catch(e) {
    Swal.fire({
      title:'Tente novamente',
      text:'Ocorreu um imprevisto, por gentileza tente novamente.',
      icon:'error',
      showConfirmButton: false,
       });
    }
  };

  const editar = async () => {
    let dados = {
      numero: linhaNumero,
      pontoIda: { id: paradaIda.dados.id },
      pontoVolta: { id: paradaVolta.dados.id },
    };

    let result = await Swal.fire({
      title: "Aviso",
      text: "Deseja realmente editar este dado? ",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Sim, desejo",
      cancelButtonText: "Não",
    });

    if (result.value) {
    try {
      const token = await localStorage.getItem("token");
      const response = await api.put(`/api/linha/${id}`, dados, {
        headers: { Authorization: token },
      });
   
      if (response.status === 200) {
        props.history.push("/linha");
        Swal.fire({
          position: 'flex-end',
          icon: 'success',
          title: 'Alterado com Sucesso',
          showConfirmButton: false,
        });    
        window.location.reload();
      } else {
        alert("Ocorreu um erro. Tente de novo");
      }
    } catch (e) {
      Swal.fire({
        title:'Tente novamente',
        text:'Ocorreu um imprevisto, por gentileza tente novamente.',
        icon:'error',
        showConfirmButton: false,
         });
    }
  }
  };

  const pesquisa = async (inputValue) => {
    const token = localStorage.getItem("token");

    const response = await api.get(`/api/pontofinal?q=${inputValue}`, {
      headers: { Authorization: token },
    });

    let opcoes = response.data.map((item) => ({
      value: item.id,
      label: item.nome,
      dados: item,
    }));

    return opcoes;
  };

  const concluir = () => {
    isEdicao ? editar() : cadastrar();
  };

  return (
    <Container>
      <CorpoPagina>
        <FormContainer>
          <Link to="/linha">
            <BotaoForm texto="VOLTAR" ladoDireito={false} />
          </Link>

          <Caixa>
            <Subtitulo>{tipoPagina} DA LINHA</Subtitulo>
            <Titulo>Dados de Acesso</Titulo>

            <InputComRotulo
              texto="Número da Linha"
              maxLength="7"
              name="numeroLinha"
              type="text"
              value={linhaNumero}
              onChange={(e) => setLinhaNumero(e.target.value)}
              required
            />

            <CaixaHorizontal>
              <Select>
                <Rotulo>Parada Ida</Rotulo>
                <AsyncSelect
                  value={paradaIda}
                  onChange={(e) => setParadaIda(e)}
                  loadOptions={(e) => pesquisa(e)}
                />
              </Select>

              <Select>
                <Rotulo>Parada Volta</Rotulo>
                <AsyncSelect
                  value={paradaVolta}
                  onChange={(e) => setParadaVolta(e)}
                  loadOptions={(e) => pesquisa(e)}
                />
              </Select>
            </CaixaHorizontal>
          </Caixa>

          <BotaoForm texto="Finalizar" concluir={true} criarJson={concluir} />
        </FormContainer>
      </CorpoPagina>
    </Container>
  );
}
